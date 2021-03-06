function onLoad(){
	if(localStorage.getItem('code') === null){
		resetCode();
		var code = editor.getValue();
		localStorage.setItem('code',code);
	}
	var code = localStorage.getItem('code');
	editor.session.setValue(code);
	if(localStorage.getItem('fontSize') !== null){
		document.getElementById('editor').style.fontSize = localStorage.getItem('fontSize') + 'px';
	}
}


function saveCode(){
	console.log(editor.getValue());
	var code = editor.getValue();
	localStorage.setItem('code',code);
	alert("Your code is saved.");
}


function submitCode(){
	console.log(editor.getValue());
	var code = editor.getValue();
	localStorage.setItem('code',code);
	var input = document.getElementById("in").value;
	document.getElementById("out").value = "";
	var xhr = new XMLHttpRequest();
	var currentMode = editor.session.$mode.$id ;
	var url = "http://localhost:8082/codeganak/run";
	var lang = "java";
	if (currentMode.localeCompare("ace/mode/python") == 0)
		lang = "python"
	console.log(url);	
	xhr.open("POST", url, true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.onreadystatechange = function () {
		if (xhr.readyState === 4 && xhr.status === 200) {
			var json = JSON.parse(xhr.responseText);
			document.getElementById('out').value = json.output;
			console.log(json.output);
		}
		else{
			document.getElementById('out').value = "Some error occurred while connecting.";
		}
	};
	var data = JSON.stringify({
		"lang": lang,
		"code": code,
		"input": input
	});
	xhr.send(data);
}

function selectLang(obj) { 	
		var selectedValue = obj.options[obj.selectedIndex].value;
		console.log(selectedValue);
		if(selectedValue.localeCompare("java") == 0){
			editor.session.setMode("ace/mode/java");
		}
		else if(selectedValue.localeCompare("python") == 0){
			editor.session.setMode("ace/mode/python");
		}	
} 

function resetCode(){
	var ifResetTrue = confirm("Your saved code will be lost.\n Do you still want to reset ?")
	if(ifResetTrue){
		var currentMode = editor.session.$mode.$id ;
		if(currentMode.localeCompare('ace/mode/python') == 0){
			editor.session.setValue("if __name__ == '__main__':\n\tprint('Welcome to CodeGanak Python.')");
			var code = editor.getValue();
			localStorage.setItem('code',code);
		}
		else if(currentMode.localeCompare('ace/mode/java') == 0){
			editor.session.setValue("import java.util.*;\nimport java.lang.*;\n\nclass Main{\n\tpublic static void main(String args[]){\n\t\tSystem.out.println(\"HELLO Java codeGanaks.\");\n\t}\n}");
			var code = editor.getValue();
			localStorage.setItem('code',code);
		}
	}
}

function changeFont(obj){
		var selectedValue = obj.options[obj.selectedIndex].value;
		console.log(selectedValue);
		document.getElementById('editor').style.fontSize = selectedValue + 'px';
		localStorage.setItem('fontSize', selectedValue);
}

