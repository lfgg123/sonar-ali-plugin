package org.sonar.java.rule.checks.namerules;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

/**
 * 描述：
 *
 * @author chentianlong
 * @create 2018/03/09 14:18
 */
public class BooleanPropertyCheckTest {
    @Test
    public void test(){
        JavaCheckVerifier.verify("src/test/files/BooleanPropertyEx.java", new BooleanPropertyCheck());
    }
}
