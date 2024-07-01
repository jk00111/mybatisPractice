
	function docTypeR(type) {
		
		switch (type) {
			case "휴가신청서":
				return "V";
			case "기안서":
				return "D";
			case "지출결의서":
				return "E";
			case "사직서":
				return "F";
			default:
				return "err";
		}
	}
	
	function lineSplit(app) {
		
		var array = app.split(',');
		
		for(i=0; i < array.length; i++){
			
			if(array[i] == undefined || array[i] == 'undefined' ){
				
				array[i].replace("");
			}
		}
		
		return array;
	}