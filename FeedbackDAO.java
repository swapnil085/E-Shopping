import java.lang.*;
import java.util.List;

public interface FeedbackDAO {
	public void getFeedbackById(int id  );
	public void addFeedback(Feedback feedback);
	public void deleteFeedbackById(int product_id);
	// public void updateStudent(Student student);
	// public void deleteStudent(Student student);
	
}

