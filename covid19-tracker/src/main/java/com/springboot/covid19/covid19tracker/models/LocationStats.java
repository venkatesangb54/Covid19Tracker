package com.springboot.covid19.covid19tracker.models;

public class LocationStats {
		public String state;
		public String country;
		public int latestTotalCases;
		public int diffCasesPrevDay;
		
		
		public int getLatestTotalCases() {
			return latestTotalCases;
		}
		public void setLatestTotalCases(int latestTotalCases) {
			this.latestTotalCases = latestTotalCases;
		}
		public int getDiffCasesPrevDay() {
			return diffCasesPrevDay;
		}
		public void setDiffCasesPrevDay(int diffCasesPrevDay) {
			this.diffCasesPrevDay = diffCasesPrevDay;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		
		
		
		@Override
		public String toString() {
			return "LocationStats [state=" + state + ", country=" + country + ", latestTotalCases=" + latestTotalCases
					+ "]";
		}
}
