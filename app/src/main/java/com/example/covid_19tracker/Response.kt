package com.example.covid_19tracker

data class Info(
	val statewise: List<StatewiseItem>
)

data class StatewiseItem(
	val statenotes: String? = null,
	val recovered: String? = null,
	val deltadeaths: String? = null,
	val migratedother: String? = null,
	val deltarecovered: String? = null,
	val active: String? = null,
	val deltaconfirmed: String? = null,
	val state: String? = null,
	val statecode: String? = null,
	val confirmed: String? = null,
	val deaths: String? = null,
	val lastupdatedtime: String? = null,
    val deltaactive: String? = null
)

