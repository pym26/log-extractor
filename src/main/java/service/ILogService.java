package service;

import java.io.IOException;
import java.util.Date;

public interface ILogService
{
	void read(final String from, String to) throws IOException;
}
