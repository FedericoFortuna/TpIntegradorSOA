$(document).ready(function() {});


  $('#combobox').on('change', function() {
    if ($("#combobox").val() == "CUIL") {
      document.getElementById("GENERO").style.display = "block";
    } else {
      document.getElementById("GENERO").style.display = "none";
    }

    if ($("#combobox").val() == "TEXT_TRANSFORM") {
      document.getElementById("TEXT_TRANSFORM").style.display = "block";
    } else {
      document.getElementById("TEXT_TRANSFORM").style.display = "none";
    }
  });

  $('#submit').click(function() {
    event.preventDefault();


    var inputTextValue = $('#inputText').val();
    var comboboxValue = $('#combobox').val();
    
    var extraValue;
    if ($("#combobox").val() == "CUIL") {
      extraValue = $('#generoCUIL').val();
    }
    if ($("#combobox").val() == "TEXT_TRANSFORM") {
      extraValue = $('#text_transform').val();
    }
    var jsonData = {
      task_type: comboboxValue,
      task_details: {
        text: inputTextValue,
        operation: extraValue
      }
    };
    $.ajax({
      url: 'http://localhost:8083/task',
      type: 'POST',
      data: JSON.stringify(jsonData),
      contentType: 'application/json',
      success: function(data) {
        $('#inputResult').val(data);
      },
      error: function(error) {
        console.log(error);
      }
    });
  });
    


  $('#getResult').click(function() {
    var inputTextValue = $('#idTarea').val();
    $.ajax({
      url: "http://localhost:8083/task/" + inputTextValue,
      type: 'GET',
      success: function(data) {
        var parsedData = JSON.parse(data);
        $('#inputResult').val(parsedData.resultado);
      },
      error: function(error) {
        console.log(error);
      }
    });
  });