package com.greedy.waterfall.common.result;

public class Result {
	
	boolean result;
	
	public Result() {
		this.result = true;
	}
	
	public Result(Integer checkValue) {
		this.result = true;
		
		if(nullCheck(checkValue)) {
			integerCheck(checkValue);
		}
	}
	public Result(boolean checkValue) {
		this.result = checkValue;
	}
	
	public Result and(Integer checkValue) {
		if(nullCheck(checkValue)) {
			integerCheck(checkValue);
		}
		
		return this;
	}

	public Result and(boolean checkValue) {

		this.result = this.result && checkValue;
		return this;
	}
	
	public Result perform(Integer checkValue) {
		if(nullCheck(checkValue)) {
			integerCheck(checkValue);
		}
		
		return this;
	}
	
	public boolean isOk() {
		
		return this.result;
	}

	public boolean result() {
		
		return this.result;
	}
	
	private boolean nullCheck(Integer checkValue) {
		if(checkValue != null) {

			return true;
		}
		this.result = false;
		
		return false;
	}

	private void integerCheck(Integer checkValue) {
		if(checkValue > 0) {
			this.result = this.result && true;
		} else {
			this.result = false;
		}
	}
}

































