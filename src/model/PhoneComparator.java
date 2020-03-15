package model;

import java.util.Comparator;

public class PhoneComparator implements Comparator<User> {

	@Override
	public int compare(User u1, User u2) {
		int comp=0;
		if(u1.getPhone().compareTo(u2.getPhone())>0) {
			comp=1;
		}else if(u1.getPhone().compareTo(u2.getPhone())<0) {
			comp=-1;
		}else {
			comp=0;
		}
		return comp;
	}
}
