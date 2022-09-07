/* Name: Harsh Goti
 * Id: 991625543
 * Date: 27th october,2021 
 * Poll.java
*/
package ca.sheridancollege.gotih.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

//define setters and getters with lombok
@Data
//define noArgsConstructor using lombok
@NoArgsConstructor
public class Poll {

	//Create a private variable
	private int pollId;
	private String title;
	private String answer1;
	private String answer2;
	private String answer3;
	private int votes1=0;
	private int votes2=0;
	private int votes3=0;
}