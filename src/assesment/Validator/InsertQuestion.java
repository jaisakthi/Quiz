package assesment.Validator;

public class InsertQuestion {
public static void validateQuestion(String q)throws Exception
{
	if(q==null || q.trim()==" ")
		throw new Exception ("Invalid question ");
}	
	
	public static void validateScore(int q)throws Exception
	{
		if(q<=0 || q>5)
			throw new Exception ("Invalid Score");
	}
	public static void validateQuestionOption(String q)throws Exception
	{
		if(q==null || q.trim()==" ")
			throw new Exception ("Invalid question");
	}	
	public static void validateInput(int q)throws Exception
	{
		if(q<0 || q>1)
			throw new Exception ("Invalid Input");
	}
	public static void validateAssname(String q)throws Exception
	{
		if(q==null || q.trim()==" ")
			throw new Exception ("Invalid Assesment Name");
	}	
	


}