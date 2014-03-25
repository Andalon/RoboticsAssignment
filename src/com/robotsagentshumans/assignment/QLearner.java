package com.robotsagentshumans.assignment;

//package edu.ucf.cap6671.learners.qlearning;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Random;

public class QLearner {
	private QEnvironment env;
	private final double GAMMA = 0.8;
	private final double ALPHA = 1;
    private final int ITERATIONS = 900;
    private final int[] INITIAL_STATES = new int[] {55,0};

    private int[][] q;
    private int currentState = 0;

    private File logFile;
    private PrintStream logger;
    
    
    public QLearner(GridWorld w){
    	logFile = new File("qlog.txt");
    	try {
			logger = logFile != null ? new PrintStream(logFile) : System.out;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	env = new QEnvironment(w);
    	q = new int[env.getSize() * env.getSize()][env.getSize() * env.getSize()];
    	
    	train();
    }
    
   	public QLearner(int[][] q){
   		this.q =  q;
	   
   	}
    private void train()
    {
    	// Perform training, starting at all initial states.
        for(int j = 0; j < ITERATIONS; j++)
        {
        	
        	int i;
        	do{
        		i = new Random().nextInt(env.getSize() * env.getSize());
        	}
        	while(env.getRewards()[QEnvironment.getPositionForState(i).getFirst()][QEnvironment.getPositionForState(i).getSecond()] < 0);
            //for(int i = 0; i < INITIAL_STATES.length; i++)
            //{
                episode(i);
                logQTable();
            //} // i
        } // j

        System.out.println("Q Matrix values:");
        int nstates = env.getSize() * env.getSize();
        //int nactions = PossibleAction.values().length;
        for(int i = 0; i < nstates; i++)
        {
            for(int j = 0; j < nstates; j++)
            {
                System.out.print(q[i][j] + ",\t");
            } // j
            System.out.print("\n");
        } // i
        System.out.print("\n");

        return;
    }
    
    private void logQTable() {
		// TODO Auto-generated method stub
    	logger.println("--------------------------------START OF Q TABLE--------------------------------\n\n");
    	for(int i = 0; i < 100; ++i){
    		for(int j = 0; j < 100; ++j){
        		logger.print(q[i][j]);
        		if(j < 99){
        			logger.print(", ");
        		}
        		else{
        			logger.println();
        		}
        	}
    	}
    	logger.println("--------------------------------END OF Q TABLE--------------------------------\n\n");
	}

	public void test()
    {
        // Perform tests, starting at all initial states.
        System.out.println("Shortest routes from initial states:");
        for(int i = 0; i < INITIAL_STATES.length; i++)
        {
            currentState = INITIAL_STATES[i];
            do
            {
            	int maxAction = 0;
            	for(int j = 1; j < 100; ++j){
            		if(q[currentState][j] > q[currentState][maxAction]){
            			maxAction = j;
            		}
            	}
                System.out.print(currentState + ", ");
                currentState = maxAction;
            }while(currentState != 33);
            System.out.println(currentState);
        }

        return;
    }
    
    private void episode(final int initialState)
    {
        currentState = initialState;
        int episodecounter = 0;
        Pair coord = QEnvironment.getPositionForState(currentState); 
        int rValue = env.getRewards()[coord.getFirst()][coord.getSecond()];
        
        // Travel from state to state until goal state is reached.
        do
        {
            chooseAnAction();
            ++episodecounter;
            coord = QEnvironment.getPositionForState(currentState); 
            rValue = env.getRewards()[coord.getFirst()][coord.getSecond()];
        }while(rValue !=  100);// && episodecounter <=150);
              
        // When goal is reached, Run through the set once more for convergence.
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
            int oldAction = -1;
            
            do{
            	oldAction = nextAction;
            	nextAction = getNextValidAction(nextAction);
            	if(reward(nextAction) > bestReward){
            		possibleAction = nextAction;
            	}
            }while(nextAction != oldAction);
        }
        else if(transitionProbability < 0.9){
        	// Randomly choose a possible action connected to the current state.
        	do{
        		possibleAction = getRandomAction(env.getSize() * env.getSize());
        	}
        	while(env.getTransitions()[currentState][possibleAction] < 0);
        }
        else{
	        //Stay
	        possibleAction = currentState;
        }
        if(env.getTransitions()[currentState][possibleAction] >= 0){
            q[currentState][possibleAction] = (int)(q[currentState][possibleAction] + ALPHA * (reward(possibleAction) - q[currentState][possibleAction]));
        	//q[currentState][possibleAction] = reward(possibleAction);
        	currentState = possibleAction;
        }
        return;
    }
    
    private int getNextValidAction(int currentAction){
    	int action = currentAction;
    	boolean choiceIsValid = false;

        // Randomly choose a possible action connected to the current state.
        while(!choiceIsValid && (currentAction < env.getSize()*env.getSize() - 1))
        {
            if(env.getTransitions()[currentState][action] > -1){
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
            if(env.getTransitions()[currentState][action] > -1){
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
            for(int i = 0; i < env.getSize() * env.getSize(); i++)
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
        return (int)(env.getTransitions()[currentState][Action] + (GAMMA * maximum(Action, false)));
    }
    
    public int[][] getQTable(){
    	return q;
    }
    
    public void run(String[] args)
    {
        train();
        test();
        return;
    }
}
