package assesment.Validator;

public class QuestionType {
public static void typeCheck(int a)throws Exception
{
	if (a==0 || a>2)
	{
		throw new Exception("Invalid choice");
}
}
}