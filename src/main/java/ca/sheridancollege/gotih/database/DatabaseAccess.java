/* Name: Harsh Goti
 * Id: 991625543
 * Date: 27th october,2021 
 * DatabaseAccess.java
*/
package ca.sheridancollege.gotih.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.gotih.beans.Poll;


@Repository
public class DatabaseAccess {

	@Autowired
	protected NamedParameterJdbcTemplate jdbc;
	
	//get all the polls from the database
	public List<Poll> getPolls(){
		String query="SELECT * FROM data";
		return jdbc.query(query,new BeanPropertyRowMapper<Poll>(Poll.class));
	}
	
	//update vote in the database
	public void addVote(Poll poll) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		System.out.println("pollId: "+poll.getPollId());
		System.out.println("VOTE1"+poll.getVotes1());
		String query = "UPDATE Polls SET Answer1="+poll.getVotes1()+", Answer2="+poll.getVotes2()+", Answer3="+poll.getVotes3()+" WHERE pollId=:pollId";
		namedParameters.addValue("pollId", poll.getPollId());
		System.out.println("added vote:"+jdbc.update(query, namedParameters));
		jdbc.update(query, namedParameters);
	}
	
	//get vote from the database
	public Integer getVotes(int pollId, int votes) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT Answer"+votes+" FROM Polls WHERE pollId=:pollId";
		namedParameters.addValue("pollId", pollId);
		return (jdbc.queryForObject(query, namedParameters, Integer.class));
	}
}
