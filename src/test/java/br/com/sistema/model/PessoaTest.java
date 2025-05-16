package br.com.sistema.model;

import br.com.sistema.utility.NegocioException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class PessoaTest {
    @Test
    public void testConstrutorPadrao() {
        Pessoa pessoa = new Pessoa();
        assertNotNull(pessoa);
        assertNull(pessoa.getId());
        assertNull(pessoa.getNome());
        assertNull(pessoa.getDataNascimento());
        assertNull(pessoa.getSexo());
        assertNotNull(pessoa.getEnderecos());
        assertTrue(pessoa.getEnderecos().isEmpty());
    }

    @Test
    public void testConstrutorComParametros() {
        Long id = 1L;
        String nome = "João Silva";
        Date dataNascimento = new Date();
        Sexo sexo = Sexo.M;
        List<Endereco> enderecos = new ArrayList<>();
        enderecos.add(new Endereco());

        Pessoa pessoa = new Pessoa(id, nome, dataNascimento, sexo, enderecos);

        assertEquals(id, pessoa.getId());
        assertEquals(nome, pessoa.getNome());
        assertEquals(dataNascimento, pessoa.getDataNascimento());
        assertEquals(sexo, pessoa.getSexo());
        assertEquals(enderecos, pessoa.getEnderecos());
        assertEquals(1, pessoa.getEnderecos().size());
    }

    @Test
    public void testGettersAndSetters() {
        Pessoa pessoa = new Pessoa();

        Long id = 2L;
        String nome = "Maria Souza";
        Date dataNascimento = new Date();
        Sexo sexo = Sexo.F;
        List<Endereco> enderecos = new ArrayList<>();

        pessoa.setId(id);
        pessoa.setNome(nome);
        pessoa.setDataNascimento(dataNascimento);
        pessoa.setSexo(sexo);
        pessoa.setEnderecos(enderecos);

        assertEquals(id, pessoa.getId());
        assertEquals(nome, pessoa.getNome());
        assertEquals(dataNascimento, pessoa.getDataNascimento());
        assertEquals(sexo, pessoa.getSexo());
        assertEquals(enderecos, pessoa.getEnderecos());
    }

    @Test
    public void testEqualsAndHashCode() {
        Pessoa pessoa1 = new Pessoa(1L, "João", new Date(), Sexo.M, new ArrayList<>());
        Pessoa pessoa2 = new Pessoa(3L, "Maria", new Date(), Sexo.F, new ArrayList<>());
        Pessoa pessoa3 = new Pessoa(2L, "João", new Date(), Sexo.M, new ArrayList<>());

        // Reflexividade
        assertEquals(pessoa1, pessoa1);
        // Simetria
        assertNotEquals(pessoa1, pessoa2);
        assertNotEquals(pessoa2, pessoa1);
        // Transividade (implícito)
        // Consistência
        assertNotEquals(pessoa1, pessoa2);
        // Não-nulo
        assertNotEquals(null, pessoa1);
        // Diferentes
        assertNotEquals(pessoa1, pessoa3);

        // HashCode
        assertNotEquals(pessoa1.hashCode(), pessoa2.hashCode());
        assertNotEquals(pessoa1.hashCode(), pessoa3.hashCode());
    }

    @Test
    public void testToString() {
        Pessoa pessoa = new Pessoa(1L, "João", new Date(1000000000L), Sexo.M, new ArrayList<>());
        String expected = "Pessoa [id=1, nome=João, dataNascimento=" + new Date(1000000000L) +
                ", sexo=M, enderecos=[]]";
        assertEquals(expected, pessoa.toString());
    }

    @Test
    public void testValidateNomeVazio() {
        Pessoa pessoa = new Pessoa();
        pessoa.setDataNascimento(new Date());
        pessoa.setSexo(Sexo.M);

        NegocioException exception = assertThrows(NegocioException.class, () -> pessoa.validate());
        assertEquals("Nome não pode ser vazio", exception.getMessage());
    }

    @Test
    public void testValidateDataNascimentoNula() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("João Silva");
        pessoa.setSexo(Sexo.M);

        NegocioException exception = assertThrows(NegocioException.class, () -> pessoa.validate());
        assertEquals("Data de nascimento não pode ser vazio", exception.getMessage());
    }

    @Test
    public void testValidateSexoNulo() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("João Silva");
        pessoa.setDataNascimento(new Date());

        NegocioException exception = assertThrows(NegocioException.class, () -> pessoa.validate());
        assertEquals("Sexo não pode ser vazio", exception.getMessage());
    }
}
