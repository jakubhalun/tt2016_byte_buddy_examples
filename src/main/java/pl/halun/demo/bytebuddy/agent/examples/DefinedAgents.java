package pl.halun.demo.bytebuddy.agent.examples;

import java.util.HashMap;
import java.util.Map;

import pl.halun.demo.bytebuddy.agent.examples.agents.AgentCreator;
import pl.halun.demo.bytebuddy.agent.examples.agents.HelloWorldAgent;

public class DefinedAgents {

	private static final Map<String, Class<? extends AgentCreator>> agents = new HashMap<String, Class<? extends AgentCreator>>();

	static {
		agents.put("helloworld", HelloWorldAgent.class);
	}

	public static AgentCreator getAgentCreatorFor(String agentName) {
		Class<? extends AgentCreator> agentCreatorClass = agents.get(agentName
				.toLowerCase());
		if (agentCreatorClass == null) {
			return null;
		}

		try {
			return agentCreatorClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			return null;
		}
	}
}
