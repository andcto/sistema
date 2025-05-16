package br.com.sistema.model;

import br.com.sistema.utility.NegocioException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class EnderecoTest {

    private Endereco endereco;

    @Before
    public void setUp() {
        endereco = new Endereco();
    }

    @Test
    public void testConstrutorPadrao() {
        assertNotNull(endereco);
        assertNull(endereco.getId());
        assertNull(endereco.getEstado());
        assertNull(endereco.getCidade());
        assertNull(endereco.getLogradouro());
        assertNull(endereco.getNumero());
        assertNull(endereco.getCep());
        assertNull(endereco.getPessoa());
    }

    @Test
    public void testConstrutorComParametros() {
        Long id = 1L;
        String estado = "SP";
        String cidade = "São Paulo";
        String logradouro = "Rua das Flores";
        Long numero = 123L;
        String cep = "01234567";

        Endereco enderecoCompleto = new Endereco(id, estado, cidade, logradouro, numero, cep);

        assertEquals(id, enderecoCompleto.getId());
        assertEquals(estado, enderecoCompleto.getEstado());
        assertEquals(cidade, enderecoCompleto.getCidade());
        assertEquals(logradouro, enderecoCompleto.getLogradouro());
        assertEquals(numero, enderecoCompleto.getNumero());
        assertEquals(cep, enderecoCompleto.getCep());
    }

    @Test
    public void testConstrutorComPessoa() {
        Pessoa pessoa = new Pessoa();
        Endereco enderecoComPessoa = new Endereco(pessoa);

        assertEquals(pessoa, enderecoComPessoa.getPessoa());
    }

    @Test
    public void testGettersAndSetters() {
        Long id = 2L;
        String estado = "RJ";
        String cidade = "Rio de Janeiro";
        String logradouro = "Avenida Brasil";
        Long numero = 456L;
        String cep = "98765432";
        Pessoa pessoa = new Pessoa();

        endereco.setId(id);
        endereco.setEstado(estado);
        endereco.setCidade(cidade);
        endereco.setLogradouro(logradouro);
        endereco.setNumero(numero);
        endereco.setCep(cep);
        endereco.setPessoa(pessoa);

        assertEquals(id, endereco.getId());
        assertEquals(estado, endereco.getEstado());
        assertEquals(cidade, endereco.getCidade());
        assertEquals(logradouro, endereco.getLogradouro());
        assertEquals(numero, endereco.getNumero());
        assertEquals(cep, endereco.getCep());
        assertEquals(pessoa, endereco.getPessoa());
    }

    @Test
    public void testGetEnderecoFormatado() {
        endereco.setLogradouro("Rua das Flores");
        endereco.setNumero(123L);
        endereco.setCidade("São Paulo");
        endereco.setEstado("SP");
        endereco.setCep("01234567");

        String expected = "Rua das Flores, 123 - São Paulo - SP, 01234567";
        assertEquals(expected, endereco.getEnderecoFormatado());
    }

    @Test
    public void testGetEnderecoFormatadoComNumeroNulo() {
        endereco.setLogradouro("Avenida Brasil");
        endereco.setNumero(null);
        endereco.setCidade("Rio de Janeiro");
        endereco.setEstado("RJ");
        endereco.setCep("98765432");

        String expected = "Avenida Brasil, null - Rio de Janeiro - RJ, 98765432";
        assertEquals(expected, endereco.getEnderecoFormatado());
    }

    @Test
    public void testValidateSuccess() throws NegocioException {
        endereco.setLogradouro("Rua Teste");
        endereco.setCidade("Cidade Teste");
        endereco.setEstado("TE");
        endereco.setCep("12345678");

        endereco.validate(); // Não deve lançar exceção
    }

    @Test(expected = NegocioException.class)
    public void testValidateLogradouroVazio() throws NegocioException {
        endereco.setLogradouro("");
        endereco.setCidade("Cidade Teste");
        endereco.setEstado("TE");
        endereco.setCep("12345678");

        endereco.validate();
    }

    @Test(expected = NegocioException.class)
    public void testValidateCidadeVazia() throws NegocioException {
        endereco.setLogradouro("Rua Teste");
        endereco.setCidade("");
        endereco.setEstado("TE");
        endereco.setCep("12345678");

        endereco.validate();
    }

    @Test(expected = NegocioException.class)
    public void testValidateEstadoVazio() throws NegocioException {
        endereco.setLogradouro("Rua Teste");
        endereco.setCidade("Cidade Teste");
        endereco.setEstado("");
        endereco.setCep("12345678");

        endereco.validate();
    }

    @Test(expected = NegocioException.class)
    public void testValidateCEPVazio() throws NegocioException {
        endereco.setLogradouro("Rua Teste");
        endereco.setCidade("Cidade Teste");
        endereco.setEstado("TE");
        endereco.setCep("");

        endereco.validate();
    }

    @Test
    public void testEqualsAndHashCode() {
        Endereco endereco1 = new Endereco(1L, "SP", "São Paulo", "Rua A", 123L, "01234567");
        Endereco endereco2 = new Endereco(1L, "RJ", "Rio", "Rua B", 456L, "98765432");
        Endereco endereco3 = new Endereco(2L, "SP", "São Paulo", "Rua A", 123L, "01234567");

        // Teste equals
        assertTrue(endereco1.equals(endereco2));
        assertFalse(endereco1.equals(endereco3));
        assertFalse(endereco1.equals(null));
        assertFalse(endereco1.equals(new Object()));

        // Teste hashCode
        assertEquals(endereco1.hashCode(), endereco2.hashCode());
        assertNotEquals(endereco1.hashCode(), endereco3.hashCode());
    }
}