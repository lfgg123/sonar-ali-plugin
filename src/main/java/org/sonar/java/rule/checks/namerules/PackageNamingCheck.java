package org.sonar.java.rule.checks.namerules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.check.Rule;
import org.sonar.java.resolve.JavaSymbol;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.ClassTree;

import java.util.regex.Pattern;

/**
 * 描述：包名应全部为小写字母和数字组成
 *
 * @author chentianlong
 * @create 2018/03/09 16:33
 */
@Rule(key = "PackageNamingCheck")
public class PackageNamingCheck extends BaseTreeVisitor implements JavaFileScanner {

    private static final Logger LOGGER = LoggerFactory.getLogger(PackageNamingCheck.class);

    private JavaFileScannerContext context;

    private Pattern pattern = Pattern.compile("^[a-z0-9]+(\\.[a-z][a-z0-9]*)*$");

    @Override
    public void scanFile(JavaFileScannerContext context) {
        this.context = context;
        scan(context.getTree());
    }


    @Override
    public void visitClass(ClassTree tree) {
        if(tree.symbol() instanceof JavaSymbol.TypeJavaSymbol){
            String fullName = ((JavaSymbol.TypeJavaSymbol) tree.symbol()).getFullyQualifiedName();
            String className = tree.simpleName().name();
            int index = fullName.indexOf(className);
            String packageName = fullName.substring(0,index - 1);
            if(!pattern.matcher(packageName).matches()){
                LOGGER.info(">>判断包名是否符合命名规范>>" + packageName);
                context.reportIssue(this,tree,"Package "+ packageName + " should be named in lowercase characters");
            }
        }
        super.visitClass(tree);
    }
}
