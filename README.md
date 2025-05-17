# Sistema de Gestão de Pessoas e Endereços

Este projeto é uma aplicação Java Web desenvolvida para realizar o cadastro e a gestão de Pessoas e seus respectivos Endereços.

---

## 🛠️ Decisões Técnicas e Arquiteturais

- **Arquitetura MVC (Model-View-Controller)** para separar dados, regras de negócio e apresentação.
- **JavaServer Faces (JSF)** escolhido para páginas dinâmicas com XHTML, integração nativa ao Java EE.
- **Tomcat 8.5** utilizado como servidor de aplicação.
- Organização em camadas:
  - **Model:** Entidades `Pessoa` e `Endereco`
  - **Controller (Managed Beans):** `PessoaMBean` e `EnderecoMBean`
  - **View:** Páginas `.xhtml` para interação com o usuário.
  - **DAO:** Padrão DAO e `ConnectionFactory` para centralizar a camada de persistência e abstrair o acesso ao banco de dados.
  - **Service:** `PessoaService` e `EnderecoService` para desacoplar as regras de negócio das operações de persistência.
  - **Utility:** `Message` e `NegocioException`, utilizando `FacesMessage` e `FacesContext` para comunicação com o usuário via tela.

---

## 📚 Justificativa do Uso de Frameworks e Bibliotecas

- **JSF:** Facilita o desenvolvimento com suporte a validações e conversões automáticas, ideal para sistemas legados.
- **PrimeFaces:** Agiliza o desenvolvimento de interfaces modernas e responsivas, compatíveis com JSF.
- **Tomcat:** Leve, rápido e amplamente utilizado para aplicações `.war` baseadas em servlets.
- **Maven:** Gerenciamento de dependências de forma segura, padronizada e prática.


