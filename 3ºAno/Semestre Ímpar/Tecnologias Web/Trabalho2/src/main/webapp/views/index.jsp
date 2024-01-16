<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Eventify</title>
    <link rel="stylesheet" href="../static/css/styles.css">
    <link rel="icon" href="../static/img/logotipo.png">
</head>
<body>
    <div id="header">
        <div id="logo">
            <h1>Eventify</h1>
        </div>
        <div id="login-button">
            <a href="/login">
                <button>Login</button>
            </a>
        </div>
    </div>
    
    <div id="wrapper">
        <div class="section">
            <h2>Eventos Realizados</h2>
            <p>Uma viagem nostálgica pelos eventos que já nos fizeram sorrir!</p>
            <a href="/eventosPassados?page=1">
                <button class="more-info-button"> Saber Mais! </button>
            </a>
        </div>

        <div class="section">
            <h2>Eventos a Decorrer</h2>
            <p>Onde a ação acontece! Não perca o momento e junte-se a nós!</p>
            <a href="/eventosADecorrer?page=1">
                <button class="more-info-button"> Saber Mais! </button>
            </a>
        </div>

        <div class="section">
            <h2>Próximos Eventos</h2>
            <p>O futuro é brilhante e cheio de diversão. Descubra o que vem por aí!</p>
            <a href="/eventosFuturos?page=1">
                <button class="more-info-button"> Saber Mais! </button>
            </a>
        </div>

        <div class="section">
            <h2>Pesquisar Eventos</h2>
            <p> Estás à procura de algo específico? Os nossos eventos são tão diversos quanto os nossos visitantes!</p>
            <a href="/eventosPesquisar">
                <button class="more-info-button"> Saber Mais! </button>
            </a>
        </div>
    </div>
    
    <div id="footer">
        <div id="footer-content">
            <div id="footer-left">
                <div class="footer-subtext" >
                    <div>
                        Official Sponsors
                    </div>
                </div>
                <div class="footer-image" style="display: inline-block;">
                    <img src="../static/img/oneplus.png" alt="oneplus" title="Oneplus!" height="70">
                </div>
            </div>
            <div id="footer-center">
                <a href="/sobreNos">
                    <button> About Us! </button>
                </a>
            </div>
            <div id="footer-right">
                <div class="foot">
                    <p class="footer-contacts">Luís Gonçalo - l51817@alunos.uevora.pt</p>
                    <p class="footer-contacts">Pedro Emílio - l52649@alunos.uevora.pt</p>
                </div>
                <div class="horizontal-divider"></div>
                <div class="footer-subtext">
                    All Rights Reserved
                </div>
            </div>
        </div>
    </div>
</body>
</html>




























