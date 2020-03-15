package model;

import java.util.Comparator;

public class DocumentNumberComparator implements Comparator<User>{
	
	@Override
	public int compare(User u1, User u2) {
		int comp=0;
		if(u1.getDocumentNumber().compareTo(u2.getDocumentNumber())>0) {
			comp=1;
		}else if(u1.getDocumentNumber().compareTo(u2.getDocumentNumber())<0) {
			comp=-1;
		}else {
			comp=0;
		}
		return comp;
	}

}
