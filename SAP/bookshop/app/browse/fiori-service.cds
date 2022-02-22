using CatalogService from '../../srv/cat-service';

//https://medium.com/nerd-for-tech/sap-tutorial-complete-cap-java-4-6938e419f575
annotate CatalogService.Books with @(UI : {
//Adding a Table Title
	HeaderInfo  : {
		TypeName : 'Book',
		TypeNamePlural : 'Books',
	},
	//Adding Default Columns and Column Labels
	LineItem : [
		{
			Value: title,
			Label: 'Title'
		},
		{Value: author},
		{Value: genre},
	    {
      		Value: rating,
		 	Label: 'Rating',
			$Type  : 'UI.DataFieldForAnnotation',
      		Target : '@UI.DataPoint#rating'
    	},
		{Value : price},
		//Hiding Fields
		{Value: descr,![@UI.Hidden]},
		{Value : currency_code,![@UI.Hidden]}
	],
	// Adding Extra Search Fields
	SelectionFields : [
		author,
		genre
	],
	//Changing Default Sort Order
	PresentationVariant : {
		Text           : 'Default',
		//add colomn for ordering
		SortOrder      : [{Property : title},{Property : genre}],
		Visualizations : ['@UI.LineItem']
	},
	DataPoint #rating   : {
    	Value         : rating,
    	Visualization : #Rating,
    	TargetValue   : 5
    }
}) {
  	@UI.HiddenFilter
  	descr;
	@Measures.ISOCurrency : currency.code
	price
};
