zk.afterMount(function() {
	
	var binder = zkbind.$('$cal'), 
		calConfig = {};
	
	// init full calendar configs here
	calConfig.header = {
		left: 'prev, next today',
		center: 'title',
		right: 'month, agendaWeek, agendaDay'
	};
	
	calConfig.timezone = 'local';
	
	// set the theme option to true;
	calConfig.theme = true;
	
	// set the event color
	calConfig.eventColor = '#5687a8';
	
	// day click handler
	calConfig.dayClick = function(data, jsEvent, view) {
		var popOffset = [jsEvent.clientX, jsEvent.clientY];
		
		binder.command('doDayClicked', {dateClicked: data.toDate().getTime()})
			.after(function() {
				var newPop = zk.$('$newEventPop');
				newPop.open(newPop, popOffset);
		});
	};
	
	// event click handler
	calConfig.eventClick = function(event, jsEvent, view) {
		var popOffset = [jsEvent.clientX, jsEvent.clientY];
		binder.command('doEventClicked', {evtId: event.id})
			.after(function() {
				var modPop = zk.$('$modifyEventPop');
				modPop.open(modPop, popOffset);
		});
	}
	
	// event drop handler and event resize handler
	calConfig.eventResize = calConfig.eventDrop = 
		function(event, delta, revertFunc, jsEvent, ui, view) {
		var startTime = event.start ? 
				event.start.toDate().getTime() : 0,
			endTime = event.end ? event.end.toDate().getTime() : 0;
		
		binder.command('doEventChanged', {evtId: event.id, startTime: startTime, endTime: endTime});
	}
	
	$('#cal').fullCalendar(calConfig);
	
	// the event handler of after 'doCommandChange' from server
	binder.after('doEventsChange', function(events) {
		$('#cal').fullCalendar('removeEvents');
		$('#cal').fullCalendar('addEventSource', events);
		$('#cal').fullCalendar('rerenderEvents');
	});

});