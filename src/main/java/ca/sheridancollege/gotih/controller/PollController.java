/* Name: Harsh Goti
 * Id: 991625543
 * Date: 27th october,2021 
 * PollController.java
*/
package ca.sheridancollege.gotih.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.gotih.beans.Poll;
import ca.sheridancollege.gotih.database.DatabaseAccess;

@Controller
public class PollController {

	//Create an object of DatabaseAccess class which will be used to access the database
	@Autowired
	DatabaseAccess da;
	
	//Define required variables
	List<Poll> polls;
	String title;
	ArrayList<String> titles;
	
	//Create and object of Poll class and the reference of that object is poll
	Poll poll=new Poll();
	String answer;
	
	
	@GetMapping("/")
	public String index(Model model) {
		
		//get the polls from getPolls() method of DatabaseAccess class
		polls = da.getPolls();
		
		//create an empty ArrayList of String type
		titles = new ArrayList<String>();
		
		//add titles in the String
		for(int i=0;i<polls.size();i++) {
			titles.add(polls.get(i).getTitle());
		}
		
		//add titles attribute in model
		model.addAttribute("titles",titles);
		
		//add poll attribute in model
		model.addAttribute("poll",poll);
		return "index";
	}
	
	@PostMapping("/selectAnswer")
	public String selectAnswer(Model model,  @ModelAttribute Poll poll ) {
		int i=0;
		title = poll.getTitle();
		while(!(poll.getTitle().equalsIgnoreCase(polls.get(i).getTitle()))) {
			i++;
		}
		model.addAttribute("poll",polls.get(i));
		
		System.out.println("You selected : "+polls.get(i).getTitle());
		return "answers";
	}
	
	@PostMapping("/addAnswer")	
	public String addAnswer(Model model,  @ModelAttribute Poll poll, @RequestParam int answer) {
		
		//If else statement to specify which button user selected
		if(title.equalsIgnoreCase("Candy Poll")) {
			
			poll.setPollId(1);
			
			//set votes in the poll object as well as in the database
			setVotes(poll, answer);
		}
		if(title.equalsIgnoreCase("Game Poll")) {
			poll.setPollId(2);
			
			//set votes in the poll object as well as in the database
			setVotes(poll, answer);
			
		}
		if(title.equalsIgnoreCase("Food")) {
			poll.setPollId(3);
			
			//set votes in the poll object as well as in the database
			setVotes(poll, answer);
			
		}
		if(title.equalsIgnoreCase("Tech")) {
			poll.setPollId(4);
			
			//set votes in the poll object as well as in the database
			setVotes(poll, answer);
		}
		
		//add percentage(Result) in the model
		model.addAttribute("percentage1",getPercentage(poll.getVotes1(),poll.getVotes1()+poll.getVotes2()+poll.getVotes3()));
		model.addAttribute("percentage2",getPercentage(poll.getVotes2(),poll.getVotes1()+poll.getVotes2()+poll.getVotes3()));
		model.addAttribute("percentage3",getPercentage(poll.getVotes3(),poll.getVotes1()+poll.getVotes2()+poll.getVotes3()));
		
		//add poll which user selected in the model
		model.addAttribute("poll",polls.get(poll.getPollId()-1));
		return "pollResult";
	}
	
	public void setVotes(Poll poll, int answer) {
		
		//If else statement to specify which button user selected
		if(answer==1) {
			
			/*getVotes method will retrieve votes from the database and 
			setVotes method will add one vote depanding on which answer 
			user selected*/
			poll.setVotes1(da.getVotes(poll.getPollId(),1)+1);
			poll.setVotes2(da.getVotes(poll.getPollId(),2));
			poll.setVotes3(da.getVotes(poll.getPollId(),3));
			
			//addVote in the database
			da.addVote(poll);
		}
		if(answer==2) {	
			
			/*getVotes method will retrieve votes from the database and 
			setVotes method will add one vote depanding on which answer 
			user selected*/
			poll.setVotes1(da.getVotes(poll.getPollId(),1));
			poll.setVotes2(da.getVotes(poll.getPollId(),2)+1);
			poll.setVotes3(da.getVotes(poll.getPollId(),3));

			//addVote in the database
			da.addVote(poll);
		}
		if(answer==3) {
			
			/*getVotes method will retrieve votes from the database and 
			setVotes method will add one vote depanding on which answer 
			user selected*/
			poll.setVotes1(da.getVotes(poll.getPollId(),1));
			poll.setVotes2(da.getVotes(poll.getPollId(),2));
			poll.setVotes3(da.getVotes(poll.getPollId(),3)+1);
			
			//addVote in the database
			da.addVote(poll);
		}
	}
	
	//Calculate the percentage
	public String getPercentage(int votes,int totalVotes) {
		int percentage = votes*100/totalVotes;
		return (""+votes+"("+percentage+")");		
	}
}
