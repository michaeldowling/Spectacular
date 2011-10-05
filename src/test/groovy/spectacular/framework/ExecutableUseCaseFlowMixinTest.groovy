package spectacular.framework

import org.junit.Test


class ExecutableUseCaseFlowMixinTest {


    @Test
    def void testMixinExecutableUseCaseFlowAndCallAction() throws Exception {

        ExecutableUseCaseFlow.loadActionImplementations("src/test/groovy/spectacular/framework/ClassToMixinWith.groovy");


    }



}
