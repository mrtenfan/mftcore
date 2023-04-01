package ru.mrtenfan.MTFCore;

import java.util.Random;

import org.apache.logging.log4j.LogManager;

public class Debuging {
	
	/**Chance of easter egg */
    public static final double chance = Math.random();
    public static Random random = new Random();
	
	/**Outputs some information to the game console
	 * Attention!MTFCore will be used instead of the mod name!
	 * @param text text of message
	 */
	public static void infoOutput(String text) {
		infoOutput(text, MTFCoreMain.modName);
	}
	
	/**Outputs some information to the game console
	 * @param text text of message
	 * @param name mod name
	 */
	public static void infoOutput(String text, String name) {
		if(text.length() > 0 && name.length() > 0)
			LogManager.getLogger(name).info(text);
	}
	
	/**Outputs some error-info to the game console
	 * Attention!MTFCore will be used instead of the mod name!
	 * @param texet text of message
	 */
	public static void errorOutput(String text) {
		errorOutput(text, MTFCoreMain.modName);
	}

	/**Outputs some error-info to the game console
	 * @param texet text of message
	 * @param name mod name
	 */
	public static void errorOutput(String text, String name) {
		if(text.length() > 0 && name.length() > 0)
			LogManager.getLogger(name).error(text);
	}

	/**Outputs some warn-info to the game console
	 * Attention!MTFCore will be used instead of the mod name!
	 * @param texet text of message
	 */
	public static void warnOutput(String text) {
		warnOutput(text, MTFCoreMain.modName);
	}

	/**Outputs some warn-info to the game console
	 * @param texet text of message
	 * @param name mod name
	 */
	public static void warnOutput(String text, String name) {
		if(text.length() > 0 && name.length() > 0)
			LogManager.getLogger(name).warn(text);
	}
	
	/**Outputs some kind of Easter egg to the game console
	 * Attention!chance can't be more than 1 and less than 0!
	 * For example, 0.05 is 5%!
	 * A chance is created once!And that doesn't change!
	 * Attention!MTFCore will be used instead of the mod name!
	 * @param text text of easter egg
	 * @param chanceEE chance of occurrence
	 */
	public static void easterEgg(String text, double chanceEE) {
		if(chance < chanceEE) {
			infoOutput(text, MTFCoreMain.modName);
		}
	}
	
	/**Outputs some kind of Easter egg to the game console
	 * Attention!chance can't be more than 1 and less than 0!
	 * For example, 0.05 is 5%!
	 * A chance is created once!And that doesn't change!
	 * @param text text of easter egg
	 * @param chanceEE chance of occurrence
	 * @param name mod name
	 */
	public static void easterEgg(String text, double chanceEE, String name) {
		if(chance < chanceEE) {
			infoOutput(text, name);
		}
	}
	
	/**Outputs some kind of Easter egg to the game console
	 * Attention!chance can't be more than 100 and less than 0!
	 * For example, 5 is 5%!
	 * A chance is recreated everytime method is called.
	 * @param text text of easter egg
	 * @param chanceEE chance of occurrence
	 */
	public static void easterEgg(String text, int chanceEE) {
		easterEgg(text, chanceEE, MTFCoreMain.modName);
	}
	
	/**Outputs some kind of Easter egg to the game console
	 * Attention!chance can't be more than 100 and less than 0!
	 * For example, 5 is 5%!
	 * A chance is recreated everytime method is called.
	 * @param text text of easter egg
	 * @param chanceEE chance of occurrence
	 * @param name mod name
	 */
	public static void easterEgg(String text, int chanceEE, String name) {
		int chance = random.nextInt(101);
		if(chance < chanceEE) {
			infoOutput(text, name);
		}
	}
}