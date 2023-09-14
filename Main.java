
public class Main {

	public static void main(String[] args) {

		PlayerStatus player1 = new PlayerStatus("Player1", 2, 26000, 20, "sniper", 100.0, 100.0);
		PlayerStatus player2 = new PlayerStatus("Player2", 2, 2000, 20, "knife", 0.0, 0.0); 
		
		PlayerStatus player3 = new PlayerStatus("Player3");
		PlayerStatus player4 = new PlayerStatus("Player4"); 
		
		PlayerStatus player5 = new PlayerStatus("Player5", 2);
		PlayerStatus player6 = new PlayerStatus("Player6", 2); 

		PlayerStatus player7 = new PlayerStatus("Player7", 2, 26000);
		PlayerStatus player8 = new PlayerStatus("Player8", 2, 26000);
		  
		System.out.println("Game Name: " + player1.getGameName());
		
		System.out.println();
 
		boolean findArtPlayer1, findArtPlayer2, findArtPlayer3;
		findArtPlayer1 = player1.findArtifact(6);
		findArtPlayer2 = player2.findArtifact(36);
		findArtPlayer3 = player3.findArtifact(36);
		
		if (findArtPlayer1 && findArtPlayer2) {
			player1.movePlayerTo(10.0, 20.0);
			player1.showState();  
			System.out.println(); 
			player2.movePlayerTo(10.0, 20.0);
			player2.showState();
			
			System.out.println();
			
			System.out.println("Should attack the opponent? " + player1.shouldAttackOpponent(player2));     
 
			System.out.println();
			
////			player3.movePlayerTo(10.0, 20.0);
//			player3.showState(); 
//			System.out.println(); 
////			player4.movePlayerTo(10.0, 20.0);
//			player4.showState();
//			
//			System.out.println();
//			
////			player5.movePlayerTo(10.0, 20.0);
//			player5.showState(); 
//			System.out.println(); 
////			player6.movePlayerTo(10.0, 20.0);
//			player6.showState();
//			
//			System.out.println();
//			
////			player7.movePlayerTo(10.0, 20.0);
//			player7.showState(); 
//			System.out.println(); 
////			player8.movePlayerTo(10.0, 20.0);
//			player8.showState();
			
			System.out.println(); 
		} else {
			System.out.println("Game over");
		}  
	}

}
