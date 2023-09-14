
public class PlayerStatus {
	private String nickname;
	private int score; 
	private int lives; 
	private int health; 
	private String weaponInHand;
	private double positionX;
	private double positionY; 
	private static final String gameName = "MyGame";  
	
	public PlayerStatus(String nickname, int lives, int score, int health, 
			String weaponInHand, double positionX, double positionY) { 
		this.nickname = nickname;
		this.lives = lives;
		this.score = score;
		this.health = health;
		this.weaponInHand = weaponInHand;
		this.positionX = positionX;
		this.positionY = positionY;
	}
	
	public PlayerStatus(String nickname) {
		this(nickname, 3, 0, 100, "knife", 0.0, 0.0);
	}
	 
	public PlayerStatus(String nickname, int lives) { 
		this(nickname, lives, 0, 100, "knife", 0.0, 0.0);
	}

	public PlayerStatus(String nickname, int lives, int score) { 
		this(nickname, lives, score, 0, "knife", 0.0, 0.0);
	} 
	
	public boolean findArtifact(int artifactCode) {  
		if (isPerfect(artifactCode)) { 
			plusScore(5000); 
			plusLives(1);   
			setHealth(100);  
		} else if (isPrime(artifactCode)) { 
			plusScore(1000); 
			plusLives(2); 
			plusHealth(25);  
		} else if (isTrap(artifactCode)) { 
			minusScore(3000);  
			minusHealth(25);  
			
			if (this.lives <= 0) {
				return false;    
			}
		} else { 
			plusScore(artifactCode);  
		} 
		
		return true;
	}  
	
	private void setScore(int score) {
		this.score = score;
	}
	
	private void plusScore(int score) {
		this.score += score;
	}
	
	private void minusScore(int score) {
		this.score -= score;
		
		if (this.score < 0) {
			setScore(0); 
		}   
	}  

	private void setLives(int lives) {
		this.lives = lives;
	}

	private void plusLives(int lives) {
		this.lives += lives;
		
		if (this.lives > 3) {
			setLives(3); 
		} 	
	}

	private void minusLives(int lives) {
		this.lives -= lives;
	}

	private void setHealth(int health) {
		this.health = health;
	}

	private void plusHealth(int health) {
		this.health += health;
		
		if (this.health > 100) {
			setHealth(100); 
		}   
	}

	private void minusHealth(int health) {
		this.health -= health;
		
		if (this.health <= 0) {
			minusLives(1);  
			setHealth(100); 
		}  
	} 
	
	public int getScore() {
		return score;
	}

	public int getLives() {
		return lives;
	}

	public int getHealth() {
		return health;
	}

	private boolean isPerfect(int n) {
		int sumDiv = 1;
		
		if (n == 1) {
			return false;
		}
		
		for (int d = 2; d <= n / 2; d++) {
			if (n % d == 0) {
				sumDiv += d; 
			}
		}
		
		if (sumDiv != n) {
			return false;
		}
		
		return true;
	}
	
	private boolean isPrime(int n) {
		if (n == 1) {
			return false;
		}
		
		for (int d = 2; d <= n / 2; d++) {
			if (n % d == 0) {
				return false;
			}
		}
		
		return true;
	}
	
	private boolean isTrap(int n) {
		int sumDigits = 0;
		
		if (n % 2 == 0) {
			while (n != 0) {
				sumDigits += n % 10;
				n /= 10;
			}
		}
		
		if (sumDigits > 0 && sumDigits % 3 == 0) {
			return true;
		}
		
		return false;
	}
	
	public boolean setWeaponInHand(String weaponInHand) {
		int weaponPrice = 0;  
		
		if (weaponInHand.equals("knife")) {
			weaponPrice = 1000; 
		} else if (weaponInHand.equals("sniper")) {
			weaponPrice = 10000; 
		} else if (weaponInHand.equals("kalashnikov")) {
			weaponPrice = 20000; 
		} else {
			//System.out.println("The weapon is not valid."); 
			return false;
		} 
		
		if (this.score < weaponPrice) {
			//System.out.println("Not enough score to buy " + weaponInHand);
			return false;
		}
		
		this.weaponInHand = weaponInHand;
		minusScore(weaponPrice);
		
		return true;
	}

	public String getWeaponInHand() {
		return weaponInHand;
	} 

	public double getPositionX() {
		return positionX;
	}
	
	public void setPositionX(double positionX) {
		this.positionX = positionX;
	}
	
	public double getPositionY() {
		return positionY;
	}
	
	public void setPositionY(double positionY) {
		this.positionY = positionY;
	}

	public String getGameName() {
		return gameName;
	} 

	public void movePlayerTo(double positionX, double positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
	} 
	 
	public String getNickname() {
		return nickname;
	}
	 
	public boolean shouldAttackOpponent(PlayerStatus opponent) {   
		if (sameWeaponInHand(opponent)) {
			return true;
		}
		
		double distance = distanceBetweenPlayers(opponent);
			
		if (distance <= 1000) {
			if (differentWeaponInHandDistanceLess1000(opponent)) {
				return true;
			}
		} 
		
		if (differentWeaponInHandDistanceGreater1000(opponent)) { 
			return true;
		}
		
		return false;
	} 
	
	private double winProbability(PlayerStatus opponent) { 
		double winProbability1 = (3 * this.health + this.score / 1000.0) / 4.0;
		double winProbability2 = (3 * opponent.health + opponent.score / 1000.0) / 4.0;
		
		if (winProbability1 >= winProbability2) {
			return winProbability1;
		}
		
		return 0;
	}
	 
	private double distanceBetweenPlayers(PlayerStatus opponent) { 
		double posX = this.positionX - opponent.positionX;
		double posY = this.positionY - opponent.positionY;
		
		return Math.sqrt(Math.pow(posX, 2) + Math.pow(posY, 2));
	}
	
	private boolean sameWeaponInHand(PlayerStatus opponent) {
		if (this.weaponInHand.equals(opponent.weaponInHand)) {
			double winProbabilityChance = winProbability(opponent);
			
			if (winProbabilityChance > 0.0) {
				//System.out.println("The probability is to win the current player");
				return true;
			} 
			//System.out.println("The probability is to win the opponent");
			//return false; 
		}
		
		return false;
	}
	
	private boolean differentWeaponInHandDistanceLess1000(PlayerStatus opponent) { 
		if (this.weaponInHand.equals("kalashnikov")) {
			//System.out.println("The probability is to win the current player");
			return true;
		} 
		
		if (opponent.weaponInHand.equals("kalashnikov")) {
			//System.out.println("The probability is to win the opponent");
			return false;
		}
		
		if (this.weaponInHand.equals("sniper")) {
			//System.out.println("The probability is to win the current player");
			return true;
		} 

		//System.out.println("The probability is to win the opponent");
		return false;
	}
		
	private boolean differentWeaponInHandDistanceGreater1000(PlayerStatus opponent) {
		if (this.weaponInHand.equals("sniper")) {
			//System.out.println("The probability is to win the current player");
			return true;
		}
		
		if (opponent.weaponInHand.equals("sniper")) {
			//System.out.println("The probability is to win the opponent");
			return false;
		} 
		
		if (this.weaponInHand.equals("kalashnikov")) {
			//System.out.println("The probability is to win the current player");
			return true;
		} 

		//System.out.println("The probability is to win the opponent");
		return false;
	}
	 
	public void showState() {
		System.out.println("Player name: " + getNickname()
					+ "\nLives: " + getLives()
					+ "\nScore: " + getScore()
					+ "\nHealth: " + getHealth()
					+ "\nActual weapon in hand: " + getWeaponInHand()
					+ "\nBuy weapon: " + setWeaponInHand("kalashnikov")
					+ "\nNew score: " + getScore()
					+ "\nNew Weapon In Hand: " + getWeaponInHand()
					+ "\nPositionX: " + getPositionX()
					+ "\nPositionY: " + getPositionY());
	} 
	
}
