package com.robotsagentshumans.assignment;

//package edu.ucf.cap6671.learners.qlearning;

import java.util.Random;

public class QLearner {
	private QEnvironment env;
	private final double GAMMA = 0.8;
    private final int ITERATIONS = 100;
    private final int INITIAL_STATES[] = new int[] {1, 3, 5, 2, 4, 0};

    private int q[][];
    private int currentState = 0;

    public QLearner(){
    	env = new QEnvironment();
    }
    
    
    private void train()
    {
    	q = new int[env.getSize()][env.getSize()];

    	// Perform training, starting at all initial states.
        for(int j = 0; j < ITERATIONS; j++)
        {
        	int i = new Random().nextInt(env.getSize());
            //for(int i = 0; i < env.getSize(); i++)
            //{
                episode(INITIAL_STATES[i]);
            //} // i
        } // j

        System.out.println("Q Matrix values:");
        for(int i = 0; i < env.getSize(); i++)
        {
            for(int j = 0; j < env.getSize(); j++)
            {
                System.out.print(q[i][j] + ",\t");
            } // j
            System.out.print("\n");
        } // i
        System.out.print("\n");

        return;
    }
    
    private void test()
    {
        // Perform tests, starting at all initial states.
        System.out.println("Shortest routes from initial states:");
        for(int i = 0; i < env.getSize(); i++)
        {
            currentState = INITIAL_STATES[i];
            int newState = 0;
            do
            {
                newState = maximum(currentState, true);
                System.out.print(currentState + ", ");
                currentState = newState;
            }while(currentState < 5);
            System.out.print("5\n");
        }

        return;
    }
    
    private void episode(final int initialState)
    {
        currentState = initialState;
        int episodecounter = 0;
        
        // Travel from state to state until goal state is reached.
        do
        {
            chooseAnAction();
            ++episodecounter;
        }while(currentState != 5 && episodecounter <=150);
              
        // When currentState = 5, Run through the set once more for convergence.
        for(int i = 0; i < env.getSize(); i++)
        {
            chooseAnAction();
        }
        return;
    }
    
    private void chooseAnAction()
    {
        int possibleAction = 0;

        double transitionProbability = new Random().nextDouble();
        if(transitionProbability < 0.6){
        	//Move to best
            int nextAction = possibleAction;
            int bestReward = -1;
            for(int i = 0; i < env.getSize(); ++i){
            	nextAction = getNextValidAction(nextAction);
            	if(reward(nextAction) > bestReward){
            		possibleAction = nextAction;
            	}
            }
        }
        else if(transitionProbability < 0.9){
        	// Randomly choose a possible action connected to the current state.
            possibleAction = getRandomAction(env.getSize());
        }
        else{
	        //Stay
	        possibleAction = currentState;
        }
        if(env.getRewards()[currentState][possibleAction] >= 0){
            q[currentState][possibleAction] = reward(possibleAction);
            currentState = possibleAction;
        }
        return;
    }
    
    private int getNextValidAction(int currentAction){
    	int action = currentAction;
    	boolean choiceIsValid = false;

        // Randomly choose a possible action connected to the current state.
        while(!choiceIsValid && (currentAction < env.getSize() - 1))
        {
            if(env.getRewards()[currentState][action] > -1){
                choiceIsValid = true;
                action = currentAction;
                break;
            }
            currentAction = currentAction + 1;
        }
    	return action;
    }
    
    private int getRandomAction(final int upperBound)
    {
        int action = 0;
        boolean choiceIsValid = false;

        // Randomly choose a possible action connected to the current state.
        while(!choiceIsValid)
        {
            // Get a random value between 0(inclusive) and 6(exclusive).
            action = new Random().nextInt(upperBound);
            if(env.getRewards()[currentState][action] > -1){
                choiceIsValid = true;
            }
        }

        return action;
    }
    
//    private void initialize()
//    {
//        for(int i = 0; i < env.getSize(); i++)
//        {
//            for(int j = 0; j < env.getSize(); j++)
//            {
//                q[i][j] = 0;
//            } // j
//        } // i
//        return;
//    }
    
    private int maximum(final int State, final boolean ReturnIndexOnly)
    {
        // If ReturnIndexOnly = True, the Q matrix index is returned.
        // If ReturnIndexOnly = False, the Q matrix value is returned.
        int winner = 0;
        boolean foundNewWinner = false;
        boolean done = false;

        while(!done)
        {
            foundNewWinner = false;
            for(int i = 0; i < env.getSize(); i++)
            {
                if(i != winner){             // Avoid self-comparison.
                    if(q[State][i] > q[State][winner]){
                        winner = i;
                        foundNewWinner = true;
                    }
                }
            }

            if(foundNewWinner == false){
                done = true;
            }
        }

        if(ReturnIndexOnly == true){
            return winner;
        }else{
            return q[State][winner];
        }
    }
    
    private int reward(final int Action)
    {
        return (int)(env.getRewards()[currentState][Action] + (GAMMA * maximum(Action, false)));
    }
    
    public void run(String[] args)
    {
        train();
        test();
        return;
    }
    
    public static void main(String[] args){
    	QLearner learner = new QLearner();
    	learner.run(args);
    }
}
