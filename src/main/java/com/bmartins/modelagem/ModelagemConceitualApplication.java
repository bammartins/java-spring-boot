package com.bmartins.modelagem;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bmartins.modelagem.domain.Categoria;
import com.bmartins.modelagem.domain.Cidade;
import com.bmartins.modelagem.domain.Cliente;
import com.bmartins.modelagem.domain.Endereco;
import com.bmartins.modelagem.domain.Estado;
import com.bmartins.modelagem.domain.Produto;
import com.bmartins.modelagem.domain.enums.TipoCliente;
import com.bmartins.modelagem.repositories.CategoriaRepository;
import com.bmartins.modelagem.repositories.CidadeRepository;
import com.bmartins.modelagem.repositories.ClienteRepository;
import com.bmartins.modelagem.repositories.EnderecoRepository;
import com.bmartins.modelagem.repositories.EstadoRepository;
import com.bmartins.modelagem.repositories.ProdutoRepository;

@SpringBootApplication
public class ModelagemConceitualApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository; 
	
	@Autowired 
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private ClienteRepository clienteRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ModelagemConceitualApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		Produto p1 = new Produto(null, "PC", 5000.00);
		Produto p2 = new Produto(null, "Mouse", 500.00);
		Produto p3 = new Produto(null, "Mesa", 50.00);
		
		Estado e1 = new Estado(null, "Minas Gerais");
		Estado e2 = new Estado(null, "Sao Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandia", e1);
		Cidade c2 = new Cidade(null, "Sao Paulo", e2);
		Cidade c3 = new Cidade(null, "Campinas", e2);
		
		Cliente cl1 = new Cliente(null, "Bruno Martins", "bruno@teste.com", TipoCliente.PESSOAFISICA);
		Cliente cl2 = new Cliente(null, "Bruno Martins Empresa", "bruno.empresa@teste.com", TipoCliente.PESSOAJURIDICA);
		
		Endereco end1 = new Endereco(null, "Rua Canaa", "07", null, "Jd. Santo André", "09132470", c2, cl1);
		Endereco end2 = new Endereco(null, "Rua Felipe Camarao", "507", null, "Utinga", "0000000", c2, cl1);
		Endereco end3 = new Endereco(null, "Alameda Cleveland", "509", null, "Jd. Santo André", "09132470", c1, cl2);
		Endereco end4 = new Endereco(null, "Rua Felipe Camarao", "507", null, "Utinga", "0000000", c3, cl2);
		
		cl1.getTelefones().addAll(Arrays.asList("985701913", "49744449"));
		cl1.getEnderecos().addAll(Arrays.asList(end1, end4));
		
		cl2.getTelefones().addAll(Arrays.asList("968372707", "44510485"));
		cl2.getEnderecos().addAll(Arrays.asList(end2, end3));
		
		e1.getCidades().addAll(Arrays.asList(c1));
		e2.getCidades().addAll(Arrays.asList(c2, c3));
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		estadoRepository.saveAll(Arrays.asList(e1, e2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		clienteRepository.saveAll(Arrays.asList(cl1, cl2));
		enderecoRepository.saveAll(Arrays.asList(end1, end2, end3, end4));
		
	}
	
	

}
