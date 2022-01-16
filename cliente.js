function consultarRestaurantes() {
  $.ajax({
    url: "http://localhost:8080/aircraft/aeronaves",
    type: "get",

    success: function(response) {
      $("#conteudo").text(JSON.stringify(response));
    }
  });
}



$("#botao").click(consultarRestaurantes);