<%-- 
    Document   : Dashboard
    Created on : 31 de mai. de 2024, 15:48:35
    Author     : Eduar
--%>

<%@page import="beans.Faturamentobean"%>
<%@page import="simples.Tabela"%>
<%@page import="simples.Contador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="./css/reset.css">
        <link rel="stylesheet" href="./css/Dashboard.css">
        <link rel="icon" href="./img/logo.png" type="image/x-icon">
    </head>

    <body>
        <header>
            <img src="./img/Header.png" alt="Logo">
        </header>
        <div id="Pagina">
            <nav>
                <li><a href="./Dashboard.jsp">INICIO</a></li>
                <li><a href="./Cliente.xhtml">Clientes</a></li>
                <li><a href="./Veiculos.xhtml">Veículos</a></li>
                <li><a href="./Aluguel.xhtml">Aluguel</a></li>
            </nav>
            <main>
                <div class="Faturamento">
                    <h1>Faturamento Registrado</h1>
                    <div class="containerCirculos">
                    <%
                        Faturamentobean addfaturamento = new Faturamentobean();
                        addfaturamento.ConsultarFaturamento();
                        out.print(" <strong>"+addfaturamento.getTotalSTR()+"</strong>");
                    %>
                    </div>
                    </br>
                    <strong>Consultar faturamento<a href="./Faturamento.xhtml"> Faturamento</a> </strong>
                </div>
                <div class="contador">
                    <div>
                        <!-- Contador de clientes -->
                        <img src="./img/Clientes].png" alt="Clientes" width="200px">
                        <h2>Total de Clientes</h2>
                        <%
                            Contador clientes = new Contador();
                            int TotalClientes = clientes.ContarClientes();
                            out.print("<h2>" + TotalClientes + "</h2>");
                        %>

                    </div>

                    <div>
                        <!-- Contador de veículos -->
                        <img src="./img/veiculos.png" alt="Veiculos" width="200px">
                        <h2>Total de Veículos</h2>
                        <%
                            int TotalVeiculos = clientes.ContarVeiculos();
                            out.print("<h2>" + TotalVeiculos + "</h2>");
                        %>
                    </div>
                    <div>
                        <!-- Contador de aluguel -->
                        <img src="./img/Alugados.png" alt="Alugados" width="200px">
                        <h2>Total de Alugueis</h2>
                        <%
                            int TotalAluguel = clientes.ContarAluguel();
                            out.print("<h2>" + TotalAluguel + "</h2>");
                        %>
                    </div>
                </div>
                <div class="veiculosAlugados">
                    <h2>Veículos Alugados</h2>
                    <table>
                        <caption>
                            Veículos Alugados <a href="./VeiculosAlugados.xhtml" target="_blank">Consulta</a>
                        </caption>
                        <thead>
                            <tr>
                                <th scope="col">Veículo</th>
                                <th scope="col">Cliente</th>
                                <th scope="col">Valor</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                Tabela adduser = new Tabela();
                                adduser.VeiculosAtrasados();
                                for (int index = 0; index < adduser.Clientes.size(); index++) {
                                    out.print("<tr>");
                                    out.print("<th scope=\"row\">" + adduser.Veiculos.get(index) + "</th>");
                                    out.print("<td>"+adduser.Clientes.get(index)+"</td>");
                                    out.print("<td>"+adduser.Valor.get(index)+"</td>");
                                    out.print("</tr>");
                                }
                            %>
                           
                        </tbody>

                    </table>
                </div>
                            
                            
                            
                            
                <div class="veiculosAlugados">
                    <h2>Veículos não entregue</h2>
                    <table>
                        <caption>
                            Veículos não entregues
                        </caption>
                        <thead>
                            <tr>
                                <th scope="col">Veículo</th>
                                <th scope="col">Cliente</th>
                                <th scope="col">Data Alugada</th>
                            </tr>
                        </thead>
                        <tbody>
                           <%
                               adduser.VeiculusAtrasados();
                               for (int index = 0; index < adduser.AtrasadosClientes.size(); index++) {
                                    out.print("<tr>");
                                    out.print("<th scope=\"row\">" + adduser.AtrasadosVeiculos.get(index) + "</th>");
                                    out.print("<td>"+adduser.AtrasadosClientes.get(index)+"</td>");
                                    out.print("<td>"+adduser.AtrasadosDatas.get(index)+"</td>");
                                    out.print("</tr>");
                                }
                           %>
                        </tbody>

                    </table>
                </div>
              
            </main>
        </div>
        <footer>
            <p>@2024 Todos direitos reservados.</p>
        </footer>
    </body>

</html>