package org.sonar.java.rule.checks.namerules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.TypeTree;
import org.sonar.plugins.java.api.tree.VariableTree;

/**
 * 描述：
 * boolean类型字段不能用is开头
 *
 * @author chentianlong
 * @create 2018/03/09 11:40
 */
@Rule(key = "BooleanPropertyCheck")
public class BooleanPropertyCheck extends BaseTreeVisitor implements JavaFileScanner{
    private static final Logger LOGGER = LoggerFactory.getLogger(BooleanPropertyCheck.class);

    private JavaFileScannerContext context;


    @Override
    public void scanFile(JavaFileScannerContext context) {
        this.context = context;
        scan(context.getTree());
    }


    @Override
    public void visitVariable(VariableTree tree) {
        TypeTree variableType = tree.type();
        String variableName = tree.simpleName().name();
        if(variableType.symbolType().name().equalsIgnoreCase("boolean")){
            if(variableName.startsWith("is")){
                LOGGER.info(">>判断boolean类型字段是否符合命名规范>>" + tree.simpleName().name());
                context.reportIssue(this,tree,"Boolean variable"+ variableName +" should not add prefix is");
            }
        }
        super.visitVariable(tree);
    }
}
