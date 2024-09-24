package pia.task.library_automation.repository;

import pia.task.library_automation.entity.WriterBook;

public interface WriterBookDAO {
	WriterBook getWriterBook(int bookId);
	String getWriterAsStr(int bookId);
	void insertWriterBook(WriterBook writerBook);
	void deleteWriterBook(WriterBook writerBook);
	void updateWriterBook(WriterBook writerBook);
	void deleteWriterBookById(int id);
}
