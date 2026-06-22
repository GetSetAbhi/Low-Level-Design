package com.demo.splitwise.split;

import com.demo.splitwise.User;

public class EqualSplit extends Split {

	public EqualSplit(User debitor) {
		super(debitor);
	}

	@Override
	public SplitType getSplitType() {
		return SplitType.EQUAL;
	}

}
