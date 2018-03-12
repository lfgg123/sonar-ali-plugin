package org.sonar.java.rule.checks.namerules;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

/**
 * 描述：
 *
 * @author chentianlong
 * @create 2018/03/12 16:10
 */
public class PackageNamingCheckTest {
    @Test
    public void test(){
        JavaCheckVerifier.verify("src/test/files/PackageNamingEx.java", new PackageNamingCheck());
    }
}
