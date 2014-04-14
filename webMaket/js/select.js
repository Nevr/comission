function selectChanged(index) {
	document.getElementById('pm').hidden = true;
	document.getElementById('fm').hidden = true;
	document.getElementById('hud').hidden = true;

	if (index == 0) {
		document.getElementById('fm').hidden = false;
	} else if (index == 1) {
		document.getElementById('pm').hidden = false;
	} else if (index == 2) {
		document.getElementById('hud').hidden = false;
	}
}