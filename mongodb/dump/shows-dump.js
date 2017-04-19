print('dump shows start');

db.shows.insertMany(
	[
		{name: "Basel", editionYear: 2015, startDate: "2015-03-05", endDate: "2015-03-15", status: "ARCHIVED"},
		{name: "Miami", editionYear: 2015, startDate: "2015-07-05", endDate: "2015-07-15", status: "ARCHIVED"},
		{name: "Hong Kong", editionYear: 2015, startDate: "2015-10-05", endDate: "2015-10-15", status: "ARCHIVED"}
	]	

);


print('dump shows complete');
