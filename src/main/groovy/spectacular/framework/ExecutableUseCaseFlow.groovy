package spectacular.framework


class ExecutableUseCaseFlow {

    Map<String, Closure> flows = new HashMap<String, Closure>();

    def static loadActionImplementations(String path) {

        def flow = new ExecutableUseCaseFlow();

        def shell = new GroovyShell();
        shell.setVariable("flow", flow);
        shell.run(new File("src/test/groovy/spectacular/framework/ClassToMixinWith.groovy"));

        for(action in flow.flows.keySet()) {

            println "ACTION:  ${action}";
            println "CLOSURE:  " + flow.flows.get(action).call();

        }


    }



    def Action(String pathToMatch, Closure closure) {

        println "You passed in:  ${pathToMatch}";
        this.flows.put(pathToMatch, closure);


    }


}
