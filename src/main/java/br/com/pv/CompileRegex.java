package br.com.pv;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class CompileRegex implements ICompileRegex {

	public Matcher execute(String regex, String subject) {

		Matcher matcher = Pattern.compile(regex).matcher(subject);
		return matcher;
	}
}