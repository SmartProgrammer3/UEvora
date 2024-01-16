const URL_FARMLISTAS = "https://magno.di.uevora.pt/tweb/t1/farmacia/list";
const URL_SEARCHVAC = "https://magno.di.uevora.pt/tweb/t1/farmacia/searchvaccine";
const URL_VACGRIPE = "https://magno.di.uevora.pt/tweb/t1/program/gripe/list";
const URL_VACCOVID = "https://magno.di.uevora.pt/tweb/t1/program/covid19/list";
const URLADDSCHE = "https://magno.di.uevora.pt/tweb/t1/schedule/add";
const URL_RSHCE = "https://magno.di.uevora.pt/tweb/t1/schedule/remove";
const URL_ADDFARM = "https://magno.di.uevora.pt/tweb/t1/program/add";
const URL_RFARM = "https://magno.di.uevora.pt/tweb/t1/program/remove";
const URL_LISTSCHE = "https://magno.di.uevora.pt/tweb/t1/schedule/list";

const ITEMS_POR_PAGINA = 5;
let paginaAtual = 1;

function httpGet (method, url, callback) {
  const request = new XMLHttpRequest();

  request.open(method, url);
  request.onload = function () {
    if (request.status === 200) {
      callback(null, JSON.parse(request.responseText));
    } else {
      callback(`Erro: ${request.status} - ${request.statusText}`, null);
    }
  }
  request.send();
}

let currentPage = 0;
const totalPages = 10;

function getNextPage() {
  currentPage = (currentPage + 1) % totalPages;
  return currentPage;
}

function getFarmacias() {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function () {
      if (this.readyState === 4 && this.status === 200) {
          var data = JSON.parse(this.responseText);
          var farmacias = data.farmacias;

          if (farmacias.length > 0) {
              var farmaciasList = document.getElementById("listaFarmacias");

              farmaciasList.innerHTML = ""; 

              farmacias.forEach(function (farmacia) {
                  var farmaciaItem = document.createElement("li");
                  farmaciaItem.textContent = `Nome: ${farmacia.name}, Localidade: ${farmacia.postal_code_locality}`;
                  farmaciasList.appendChild(farmaciaItem);
              });
          } else {
              console.log("Nenhuma farmácia encontrada.");
          }
      }
  };

  xhttp.open("POST", URL_FARMLISTAS, true);
  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  xhttp.send("page=1");
}

function searchFarmaciasNome() {
  var xhttp = new XMLHttpRequest();
  var nomeFarmacia = document.getElementById("nFarmacia").value;

  xhttp.onreadystatechange = function () {
      if (this.readyState == 4) {
          console.log("Status da requisição:", this.status);
          console.log("Resposta da requisição:", this.responseText);
          if (this.status == 200) {
              var data = JSON.parse(this.responseText);

              if (data.farmacias && data.farmacias.length > 0) {
                  var farmacias = data.farmacias;
                  var datadiv = document.getElementById("listaFarmacias");
                  datadiv.innerHTML = "";

                  farmacias.forEach(function (farmacia) {
                      var farmaciaI = document.createElement("li");
                      farmaciaI.textContent = `Nome: ${farmacia.name}, Localidade: ${farmacia.postal_code_locality}`;
                      datadiv.appendChild(farmaciaI);
                  });
              } else {
                  var datadiv = document.getElementById("procura");
                  datadiv.textContent = "Nenhuma farmácia encontrada.";
              }
          } else {
              console.error("Erro na solicitação. Status: " + this.status);
          }
      }
  };

  var requestBody = "name=" + nomeFarmacia;

  xhttp.open("POST", URL_SEARCHVAC, true);
  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  xhttp.send(requestBody);
}

function searchFarmaciasLocalidade() {
  var xhttp = new XMLHttpRequest();
  var localidadeFarmacia = document.getElementById("localidade").value;

  xhttp.onreadystatechange = function () {
      if (this.readyState == 4) {
          console.log("Status da requisição:", this.status);
          console.log("Resposta da requisição:", this.responseText);
          if (this.status == 200) {
              var data = JSON.parse(this.responseText);

              if (data.farmacias && data.farmacias.length > 0) {
                  var farmacias = data.farmacias;
                  var datadiv = document.getElementById("listaFarmacias");
                  datadiv.innerHTML = "";

                  farmacias.forEach(function (farmacia) {
                      var farmaciaI = document.createElement("li");
                      farmaciaI.textContent = `Nome: ${farmacia.name}, Localidade: ${farmacia.postal_code_locality}`;
                      datadiv.appendChild(farmaciaI);
                  });
              } else {
                  var datadiv = document.getElementById("procura");
                  datadiv.textContent = "Nenhuma farmácia encontrada.";
              }
          } else {
              console.error("Erro na solicitação. Status: " + this.status);
          }
      }
  };

  var requestBody = "postal_code_locality=" + localidadeFarmacia;

  xhttp.open("POST", URL_SEARCHVAC, true);
  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  xhttp.send(requestBody);
}

function removeAccents(text) {
  return text.normalize("NFD").replace(/[\u0300-\u036f]/g, "").toLowerCase();
}

//Criar as listas das farmácias com a vacina da Covid ou da Gripe

function criarListaGripe() {
  var xhttp = new XMLHttpRequest();

  xhttp.onreadystatechange = function () {
    if (this.readyState === 4) {
      if (this.status === 200) {
        var dados = JSON.parse(this.responseText);

        var farmacias = dados.farmacias;
        var listaFarmacias = document.getElementById("listaFarmaciasGripe");

        if (farmacias.length === 0) {
          console.log('Não foram encontradas farmácias.');
          return;
        }

        farmacias.forEach(function(farmacia) {
          var itemLista = document.createElement("li");
          itemLista.textContent = `Nome: ${farmacia.name}, Localidade: ${farmacia.postal_code_locality}`;
          listaFarmacias.appendChild(itemLista);
        });
      } else {
        console.error("Erro ao obter lista de farmácias. Status:", this.status);
      }
    }
  };

  xhttp.open("GET", URL_VACGRIPE, true);
  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  xhttp.send();
}

function criarListaCovid() {
  var xhttp = new XMLHttpRequest();

  xhttp.onreadystatechange = function () {
    if (this.readyState === 4) {
      if (this.status === 200) {
        try {
          var dados = JSON.parse(this.responseText);
          var farmacias = dados.farmacias;
          var listaFarmacias = document.getElementById("listaFarmaciasCovid");

          if (farmacias.length === 0) {
            console.log('Não foram encontradas farmácias.');
            return;
          }

          farmacias.forEach(function (farmacia) {
            var itemLista = document.createElement("li");
            itemLista.textContent = `Nome: ${farmacia.name}, Localidade: ${farmacia.postal_code_locality}`;
            listaFarmacias.appendChild(itemLista);
          });
        } catch (error) {
          console.error("Erro ao fazer parse da resposta JSON:", error);
        }
      } else {
        console.error("Erro na solicitação. Status: " + this.status);
      }
    }
  };

  xhttp.open("GET", URL_VACCOVID, true);
  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  xhttp.send();
}

//Gráficos de localidade Covid e Gripe

function criarGraficoLocalidadesGripe() {
  var xhttp = new XMLHttpRequest();

  xhttp.onreadystatechange = function () {
    if (this.readyState === 4) {
      if (this.status === 200) {
        var dados = JSON.parse(this.responseText);
        var farmacias = dados.farmacias;

        var contagemLocalidades = {};

        farmacias.forEach(function (farmacia) {
          var localidade = farmacia.postal_code_locality;

          contagemLocalidades[localidade] = (contagemLocalidades[localidade] || 0) + 1;
        });

        var localidades = Object.keys(contagemLocalidades);
        var contagens = Object.values(contagemLocalidades);

        var ctx = document.getElementById('graficoLocalidades').getContext('2d');
        var grafico = new Chart(ctx, {
          type: 'bar',
          data: {
            labels: localidades,
            datasets: [{
              label: 'Número de Farmácias da Gripe',
              data: contagens,
              backgroundColor: 'rgba(75, 192, 192, 0.2)',
              borderColor: 'rgba(75, 192, 192, 1)',
              borderWidth: 1
            }]
          },
          options: {
            scales: {
              y: {
                beginAtZero: true
              }
            }
          }
        });
      } else {
        console.error('Erro ao obter dados das farmácias. Status:', this.status);
      }
    }
  };

  xhttp.open('GET', URL_VACGRIPE, true);
  xhttp.send();
}

function criarGraficoLocalidadesCovid() {
  var xhttp = new XMLHttpRequest();

  xhttp.onreadystatechange = function () {
    if (this.readyState === 4) {
      if (this.status === 200) {
        var dados = JSON.parse(this.responseText);
        var farmacias = dados.farmacias;

        var contagemLocalidades = {};

        farmacias.forEach(function (farmacia) {
          var localidade = farmacia.postal_code_locality;

          contagemLocalidades[localidade] = (contagemLocalidades[localidade] || 0) + 1;
        });

        var localidades = Object.keys(contagemLocalidades);
        var contagens = Object.values(contagemLocalidades);

        var ctx = document.getElementById('graficoLocalidadesCovid').getContext('2d');
        var grafico = new Chart(ctx, {
          type: 'bar',
          data: {
            labels: localidades,
            datasets: [{
              label: 'Número de Farmácias da Covid',
              data: contagens,
              backgroundColor: 'rgba(75, 192, 192, 0.2)',
              borderColor: 'rgba(75, 192, 192, 1)',
              borderWidth: 1
            }]
          },
          options: {
            scales: {
              y: {
                beginAtZero: true
              }
            }
          }
        });
      } else {
        console.error('Erro ao obter dados das farmácias. Status:', this.status);
      }
    }
  };

  xhttp.open('GET', URL_VACCOVID, true);
  xhttp.send();
}

//Parte do Utente, ou seja, fazer, ver e remover agendamentos

function agendarVacina(farmaciaId, vacinaTipo, data) {
  var xhttp = new XMLHttpRequest();

  var user_idInput = document.getElementById("userID");
  var user_id = user_idInput.value;

  var vaccine_input = document.getElementById("vaccine");
  var vaccine = vaccine_input.value;

  var entityId_input = document.getElementById("entityID");
  var entityId = entityId_input.value;

  var schedule_date_input = document.getElementById("scheduleDate");
  var schedule_date = schedule_date_input.value;
  
  xhttp.onreadystatechange = function () {
      if (this.readyState === 4) {
          if (this.status === 200) {
              var resposta = JSON.parse(this.responseText);
              console.log(resposta);
          } else {
              console.error("Erro ao agendar vacinação. Status:", this.status);
          }
      }
  };

  console.log("user_id:", user_id);

  var requestBody = "schedule_date=" + schedule_date + "&entity_id=" + entityId + "&user_id=" + user_id + "&vaccine=" + vaccine;

  xhttp.open("POST", URL_ADDSCHE, true);
  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  xhttp.send(requestBody);
}

function listarAgendamentos() {
  var xhttp = new XMLHttpRequest();

  var user_idInput = document.getElementById("userID");
  var user_id = user_idInput.value;

  xhttp.onreadystatechange = function () {
      if (this.readyState === 4) {
          if (this.status === 200) {
              var agendamentos = JSON.parse(this.responseText);
              exibirAgendamentos(agendamentos);
          } else {
              console.error("Erro ao obter lista de agendamentos. Status:", this.status);
          }
      }
  };

  var requestBody = "&user_id=" + user_id + "&paginaAtual=" + paginaAtual + "&itens_por_pagina=" + ITEMS_POR_PAGINA;

  xhttp.open("POST", URL_LISTSCHE, true);
  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  xhttp.send(requestBody);
}

function exibirAgendamentos(agendamentos) {
  var listaAgendamentos = document.getElementById("listaAgendamentos");
  listaAgendamentos.innerHTML = ""; 

  if (agendamentos.status === "ok") {
    var scheduleList = agendamentos.schedule_list;

    scheduleList.forEach(function (agendamento) {
      var itemLista = document.createElement("li");
      itemLista.textContent = `Farmácia: ${agendamento[0]}, Data: ${agendamento[1]}, Vacina: ${agendamento[2]}`;
      listaAgendamentos.appendChild(itemLista);
    });
  } else {
    var itemLista = document.createElement("li");
    itemLista.textContent = "Nenhum agendamento encontrado.";
    listaAgendamentos.appendChild(itemLista);
  }
}

function removerAgendamento() {
  var xhttp = new XMLHttpRequest();

  var user_idInput = document.getElementById("userID");
  var user_id = user_idInput.value;

  var agendamentoIdInput = document.getElementById("agendamentoID");
  var agendamentoId = agendamentoIdInput.value;

  xhttp.onreadystatechange = function () {
      if (this.readyState === 4) {
          if (this.status === 200) {
              var resposta = JSON.parse(this.responseText);
              if (resposta.status === 'ok') {
                  console.log("Agendamento removido com sucesso!");
              } else {
                  console.error("Erro ao remover agendamento. Status:", resposta.status);
              }
          } else {
              console.error("Erro ao remover agendamento. Status:", this.status);
          }
      }
  };

  var requestBody = "&schedule_code=" + agendamentoId + "&user_id=" + user_id;

  xhttp.open("POST", URL_RSHCE, true);
  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  xhttp.send(requestBody);
}

//Adicionar ou remover farmácias da vacina do Covid ou Gripe

function adicionarFarmacia () {
  var xhttp = new XMLHttpRequest();

  var farmaciaIdInput = document.getElementById("farmaciaID");
  var farmaciaId = farmaciaIdInput.value;

  let name = "Farmácia do Marquês";
  let postalCode = "LISBOA";
  let services = "gripe";
  
  xhttp.onreadystatechange = function () {
      if (this.readyState === 4) {
          if (this.status === 200) {
              var resposta = JSON.parse(this.responseText);
              console.log(resposta);
              criarListaGripe();
          } else {
              console.error("Erro ao agendar vacinação. Status:", this.status);
          }
      }
  };

  var requestBody = "entity_id="  + farmaciaId + "&services=" + services + "&name=" + name + "&postal_code_locality=" + postalCode;

  xhttp.open("POST", URL_ADDFARM, true);
  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  xhttp.send(requestBody);
}

function removerFarmacia () {
  var xhttp = new XMLHttpRequest();

  var farmaciaIdInput = document.getElementById("farmaciaID");
  var farmaciaId = farmaciaIdInput.value;

  xhttp.onreadystatechange = function () {
      if (this.readyState === 4) {
          if (this.status === 200) {
              var resposta = JSON.parse(this.responseText);
              if (resposta.status === 'ok') {
                  console.log("Farmácia removida com sucesso!");
              } else {
                  console.error("Erro ao remover Farmácia. Status:", resposta.status);
              }
          } else {
              console.error("Erro ao remover Farmácia. Status:", this.status);
          }
      }
  };

  var requestBody = "entity_id=" + farmaciaId;

  xhttp.open("POST", URL_RFARM, true);
  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  xhttp.send(requestBody);
}

function adicionarFarmaciaCovid () {
  var xhttp = new XMLHttpRequest();

  var farmaciaIdInput = document.getElementById("farmaciaID");
  var farmaciaId = farmaciaIdInput.value;

  let name = "Farmácia do Marquês";
  let postalCode = "LISBOA";
  let services = "gripe";
  
  xhttp.onreadystatechange = function () {
      if (this.readyState === 4) {
          if (this.status === 200) {
              var resposta = JSON.parse(this.responseText);
              console.log(resposta);
              criarListaGripe();
          } else {
              console.error("Erro ao agendar vacinação. Status:", this.status);
          }
      }
  };

  var requestBody = "entity_id="  + farmaciaId + "&services=" + services + "&name=" + name + "&postal_code_locality=" + postalCode;

  xhttp.open("POST", URL_ADDFARM, true);
  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  xhttp.send(requestBody);
}

function removerFarmaciaCovid () {
  var xhttp = new XMLHttpRequest();

  var farmaciaIdInput = document.getElementById("farmaciaID");
  var farmaciaId = farmaciaIdInput.value;

  xhttp.onreadystatechange = function () {
      if (this.readyState === 4) {
          if (this.status === 200) {
              var resposta = JSON.parse(this.responseText);
              if (resposta.status === 'ok') {
                  console.log("Farmácia removida com sucesso!");
              } else {
                  console.error("Erro ao remover Farmácia. Status:", resposta.status);
              }
          } else {
              console.error("Erro ao remover Farmácia. Status:", this.status);
          }
      }
  };

  var requestBody = "entity_id=" + farmaciaId;

  xhttp.open("POST", URL_RFARM, true);
  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  xhttp.send(requestBody);
}