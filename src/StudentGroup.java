import java.util.Date;
import java.util.concurrent.*;

/**
 * A fix-sized array of students
 * array length should always be equal to the number of stored elements
 * after the element was removed the size of the array should be equal to the number of stored elements
 * after the element was added the size of the array should be equal to the number of stored elements
 * null elements are not allowed to be stored in the array
 * 
 * You may add new methods, fields to this class, but DO NOT RENAME any given class, interface or method
 * DO NOT PUT any classes into packages
 *
 */
public class StudentGroup implements StudentArrayOperation {

	private Student[] students;
	
	/**
	 * DO NOT remove or change this constructor, it will be used during task check
	 * @param length
	 */
	public StudentGroup(int length) {
		this.students = new Student[length];
	}

	@Override
	public Student[] getStudents() {
		// Add your implementation here
		if ( students.length!=0 )
			return students;
		return null;
	}

	@Override
	public void setStudents(Student[] students) {
		// Add your implementation here
		if( students==null )
			throw new IllegalArgumentException();
		else {
			this.students=students;
		}
	}

	@Override
	public Student getStudent(int index) {
		// Add your implementation here
		if( index<0 || index>=students.length )
			throw new IllegalArgumentException();
		return students[index];
	}

	@Override
	public void setStudent(Student student, int index) {
		// Add your implementation here
		if( index<0 || index>=students.length )
			throw new IllegalArgumentException();
		if( student==null )
			throw new IllegalArgumentException();
		else {
			students[index]=student;
		}
	}

	@Override
	public void addFirst(Student student) {
		// Add your implementation here
		if( student==null )
			throw new IllegalArgumentException();
		else {
			Student tmp[] = new Student[students.length+1];
			for(int i=0;i<students.length;i++)
				tmp[i+1]=students[i];
			tmp[0] = student;
			students=tmp;
		}
	}

	@Override
	public void addLast(Student student) {
		// Add your implementation here
		if( student==null )
			throw new IllegalArgumentException();
		else {
			Student tmp[] = new Student[students.length+1];
			for(int i=0;i<students.length;i++)
				tmp[i] = students[i];
			tmp[students.length] = student;
			students = tmp;
		}
	}

	@Override
	public void add(Student student, int index) {
		// Add your implementation here
		if( index<0 || index>=students.length )
			throw new IllegalArgumentException();
		if( student==null )
			throw new IllegalArgumentException();
		else {
			int left = index+1;
			int right = students.length - index;
			Student leftS[] = new Student[left];
			Student rightS[] = new Student[right];
			for(int i=0;i<index;i++)
				leftS[i]=students[i];
			leftS[index] = student;
			for(int i=0;i<right;i++)
				rightS[i] = students[i];
			for(int i=0;i<=index;i++)
				students[i]=leftS[i];
			for(int i=0;i<right;i++)
				students[index+1+i]=rightS[i];
		}
	}

	@Override
	public void remove(int index) {
		// Add your implementation here
		if( index<0  || index>=students.length )
			throw new IllegalArgumentException();
		else {
			Student tmp[] = new Student[students.length-1];
			for(int i=0;i<index;i++)
				tmp[i] = students[i];
			int j=index;
			for(int i=index+1;i<students.length;i++) {
				tmp[j] = students[i];
				j++;
			}
			students = tmp;
		}
	}

	@Override
	public void remove(Student student) {
		// Add your implementation here
		if( student==null )
			throw new IllegalArgumentException();
		else {
			int modified = 0;
			for(int i=0;i<students.length;i++) {
				if( student.getId() == students[i].getId() ) {
					remove(i);
					modified++;
					break;
				}
			}
			if( modified==0 )
				throw new IllegalArgumentException("Student not exist");
		}
	}

	@Override
	public void removeFromIndex(int index) {
		// Add your implementation here
	}

	@Override
	public void removeFromElement(Student student) {
		// Add your implementation here
	}

	@Override
	public void removeToIndex(int index) {
		// Add your implementation here
	}

	@Override
	public void removeToElement(Student student) {
		// Add your implementation here
	}

	@Override
	public void bubbleSort() {
		// Add your implementation here
		for(int i=0;i<students.length;i++) {
			for(int j=0;j<students.length-1;j++) {
				if( students[j+1].getId() < students[j].getId() ) {
					Student tmp = students[j];
					students[j] = students[j+1];
					students[j+1] = tmp;
				}
			}
		}
	}

	@Override
	public Student[] getByBirthDate(Date date) {
		// Add your implementation here
		if( date==null ) {
			throw new IllegalArgumentException();
		}
		else {
			Student all[] = new Student[students.length];
			int j=0;
			for(int i=0;i<students.length;i++) {
				if( (students[i].getBirthDate()).equals(date) ) {
					all[j] = students[i];
					j++;
				}
			}
			return all;
		}
	}

	@Override
	public Student[] getBetweenBirthDates(Date firstDate, Date lastDate) {
		// Add your implementation here
		if( firstDate==null || lastDate==null)
			throw new IllegalArgumentException();
		else {
			Student all[] = new Student[students.length];
			int j=0;
			for(int i=0;i<students.length;i++) {
				if( students[i].getBirthDate().after(firstDate) && students[i].getBirthDate().before(lastDate) ) {
					all[j]=students[i];
					j++;
				}
				if( students[i].getBirthDate().equals(firstDate) ) {
					all[j] = students[i];
					j++;
				}
				if( students[i].getBirthDate().equals(lastDate) ) {
					all[j]=students[i];
					j++;
				}
			}
			return all;

		}
	}
	//number of days @source: stackoverflow
	public long getDifferenceDays(Date d1, Date d2) {
    	long diff = d2.getTime() - d1.getTime();
    	return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	@Override
	public Student[] getNearBirthDate(Date date, int days) {
		// Add your implementation here
		if( date==null )
			throw new IllegalArgumentException();
		else {
			Student all[] = new Student[students.length];
			int j=0;
			for(int i=0;i<students.length;i++) {
				if( getDifferenceDays(students[i].getBirthDate(),date)<=days ) {
					all[j]=students[i];
					j++;
				}
			}
			return all;
		}
	}

	@Override
	public int getCurrentAgeByDate(int indexOfStudent) {
		// Add your implementation here
		if( indexOfStudent==0 ) {
			throw new IllegalArgumentException();
		}
		else {
			Date today = new Date();
			int year = today.getYear();
			int month = today.getMonth();
			int date = today.getDate();
			Date current = new Date(year,month,date);
			Date birthdate = students[indexOfStudent].getBirthDate();
			long indays = getDifferenceDays(birthdate,current);
			int inyears = (int)indays/365;
			return inyears;
		}
	}
	public long getAgeByDate(Date date) {
		Date today = new Date();
		int y = today.getYear();
		int m = today.getMonth();
		int d = today.getDate();
		Date current = new Date(y,m,d);
		long indays = getDifferenceDays(date,current);
		long inyears = indays/365;
		return inyears;
	}
	@Override
	public Student[] getStudentsByAge(int age) {
		// Add your implementation here
		Student all[] = new Student[students.length];
		int j=0;
		for(int i=0;i<students.length;i++) {
			if( (int)getAgeByDate(students[i].getBirthDate())==age ) {
				all[j]=students[i];
				j++;
			}
		}
		return all;
	}

	@Override
	public Student[] getStudentsWithMaxAvgMark() {
		// Add your implementation here
		double sum=0;
		for(int i=0;i<students.length;i++)
			sum+=students[i].getAvgMark();
		int avg = (int)sum/students.length;
		Student all[] = new Student[students.length];
		int j=0;
		for(int i=0;i<students.length;i++) {
			if( (int)students[i].getAvgMark()>=avg ) {
				all[j]=students[i];
				j++;
			}
		}
		return all;
	}

	@Override
	public Student getNextStudent(Student student) {
		// Add your implementation here
		return null;
	}
}
