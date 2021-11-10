package com.example.covid_19tracker

data class StateInfo(
	val delta7: Stats? = null,
	val meta: Meta? = null,
	val total: Stats? = null,
	var nameOfState:String?=null
)

data class Stats(
	val confirmed:String? = null,
	val deceased:String? = null,
	val recovered:String? = null,
	val vaccinated1:String? = null,
	val vaccinated2:String? = null
)

data class Meta(
	val last_updated:String? =null
)

val mapStateCodes = mapOf(
	"TT" to "Total",
	"AN" to "Andaman and Nicobar Islands",
	"AP" to "Andhra Pradesh",
	"AR" to "Arunachal Pradesh",
	"AS" to "Assam",
	"BR" to "Bihar",
	"CH" to "Chandigarh",
	"CT" to "Chhattisgarh",
	"DN" to "Dadra and Nagar Haveli and Daman and Diu",
	"DL" to "Delhi",
	"GA" to "Goa",
	"GJ" to "Gujarat",
	"HR" to "Haryana",
	"HP" to "Himachal Pradesh",
	"JK" to "Jammu and Kashmir",
	"JH" to "Jharkhand",
	"KA" to "Karnataka",
	"KL" to "Kerala",
	"LA" to "Ladakh",
	"LD" to "Lakshadweep",
	"MP" to "Madhya Pradesh",
	"MH" to "Maharashtra",
	"MN" to "Manipur",
	"ML" to "Meghalaya",
	"MZ" to "Mizoram",
	"NL" to "Nagaland",
	"OR" to "Odisha",
	"PY" to "Puducherry",
	"PB" to "Punjab",
	"RJ" to "Rajasthan",
	"SK" to "Sikkim",
	"TN" to "Tamil Nadu",
	"TG" to "Telangana",
	"TR" to "Tripura",
	"UP" to "Uttar Pradesh",
	"UT" to "Uttarakhand",
	"WB" to "West Bengal"
)