# Calculadora de IMC

## 1. Descrição do Projeto

O projeto **Calculadora de IMC** é um aplicativo Android desenvolvido em Kotlin com Jetpack Compose, criado para a 2ª Avaliação Prática da disciplina Laboratório de Programação para Dispositivos Móveis (LPDM).

O aplicativo permite calcular o Índice de Massa Corporal (IMC) a partir do peso e da altura informados pelo usuário. Após pressionar o botão **Calcular**, o sistema apresenta o resultado numérico e sua respectiva classificação.

O aplicativo também possui uma opção para arredondar o resultado do IMC para o número inteiro mais próximo. Caso o peso ou a altura sejam inválidos, o aplicativo informa que os dados são inválidos.

### Fórmula utilizada

IMC = peso (kg) / altura² (m)

**Exemplo:** para um peso de 70 kg e uma altura de 1,75 m, o IMC calculado é aproximadamente 22,86, correspondente à classificação "Peso normal".

### Classificação do IMC

| Faixa de IMC | Classificação |
|---|---|
| Menor que 18,5 | Abaixo do peso |
| De 18,5 até 24,9 | Peso normal |
| De 25,0 até 29,9 | Sobrepeso |
| 30,0 ou mais | Obesidade |

## 2. Tecnologias Utilizadas

- **Kotlin:** linguagem de programação utilizada no desenvolvimento da aplicação.
- **Jetpack Compose:** ferramenta para construção da interface gráfica de forma declarativa.
- **Material 3:** componentes visuais utilizados na interface.
- **Android Studio:** ambiente de desenvolvimento integrado (IDE).
- **Gradle:** ferramenta de automação de compilação e gerenciamento das dependências do projeto.
- **Git e GitHub:** ferramentas utilizadas para controle de versão e hospedagem do código-fonte.

**Versão do Android Studio:** preencher com a versão instalada no ambiente de desenvolvimento.

## 3. Como Compilar e Executar o Projeto

### Pré-requisitos

- Android Studio instalado e configurado.
- SDK do Android compatível com o projeto.
- Git instalado.
- Emulador Android configurado ou dispositivo físico com depuração USB habilitada.

### Passo 1 — Clonar o repositório


### Passo 2 — Acessar a pasta do projeto


### Passo 3 — Abrir no Android Studio

1. Abra o Android Studio.
2. Selecione a opção **Open**.
3. Localize e selecione a pasta do projeto clonado.
4. Aguarde a sincronização do Gradle e a configuração das dependências.

### Passo 4 — Executar a aplicação

1. Selecione um emulador Android ou conecte um dispositivo físico configurado.
2. Aguarde a conclusão da sincronização e da compilação.
3. Clique no botão **Run (▶)** do Android Studio.
4. Aguarde a instalação e a abertura do aplicativo.

### Passo 5 — Utilizar a calculadora

1. Informe o peso em quilogramas.
2. Informe a altura em metros.
3. Ative ou desative a opção **Arredondar resultado**, conforme desejado.
4. Clique em **Calcular**.
5. Consulte o valor do IMC e a classificação apresentada na tela.

## 4. Arquivos e Funções do Projeto

### `MainActivity.kt`

Arquivo responsável pela atividade principal e pelas funções utilizadas na interface e no cálculo do IMC.

**`MainActivity`**

- Representa a atividade principal do aplicativo.
- Inicializa a interface por meio de `setContent`.
- Aplica o tema `CalculadoraDeIMCTheme` e o componente `Surface`.
- Chama o Composable `CalculadoraimcLayout()` para exibir a tela.

**`CalculadoraimcLayout()`**

- Organiza os elementos da interface em uma coluna.
- Mantém os estados dos campos de peso e altura.
- Controla o estado do Switch de arredondamento.
- Executa o cálculo quando o botão é pressionado.
- Exibe o resultado e a classificação do IMC.
- Trata entradas inválidas utilizando conversão numérica segura.

**`EditNumberField()`**

- Composable reutilizável para os campos de entrada.
- Recebe o rótulo, o valor atual, a função de atualização e o modificador visual.
- Utiliza `TextField` e `KeyboardType.Number`.
- Implementa *state hoisting* por meio dos parâmetros `value` e `onValueChange`.

**`ArredondarRow()`**

- Organiza o texto explicativo e o componente `Switch` em uma linha.
- Recebe o estado booleano `arredondar` e a função `onArredondarChanged`.
- Permite controlar a opção de arredondamento sem manter o estado internamente.

**`calcularIMC()`**

- Função independente da interface gráfica.
- Recebe o peso e a altura como valores `Double`.
- Calcula o IMC utilizando a fórmula peso dividido pela altura ao quadrado.
- Não é uma função Composable e não utiliza o modificador `private`.

**`obterClassificacao()`**

- Recebe o valor calculado do IMC.
- Utiliza a expressão `when` para determinar a classificação.
- Consulta os limites centralizados no objeto `LimitesIMC`.

**`CalculadoraIMCPreview()`**

- Permite visualizar uma prévia da interface no Android Studio.
- Aplica o tema do aplicativo e chama `CalculadoraimcLayout()`.

### `LimitesIMC`

Objeto responsável por centralizar os limites utilizados na classificação do IMC:

- `LIMITE_ABAIXO_PESO`: 18,5.
- `LIMITE_PESO_NORMAL`: 24,9.
- `LIMITE_SOBREPESO`: 29,9.

Essa organização evita a repetição de números mágicos na função de classificação e facilita a manutenção do código.

### Arquivos de configuração e tema

- **`build.gradle.kts` e `settings.gradle.kts`:** configuram a compilação e a estrutura do projeto.
- **`gradle.properties`:** contém propriedades de configuração do Gradle.
- **`gradlew` e `gradlew.bat`:** permitem executar o Gradle em sistemas Linux/macOS e Windows, respectivamente.
- **`gradle/`:** contém os arquivos relacionados ao Gradle Wrapper.
- **`local.properties`:** registra configurações locais, como o caminho do SDK Android; não deve ser enviado ao repositório.
- **`ui/theme/`:** contém os arquivos de configuração visual do tema, incluindo cores, tipografia e o tema `CalculadoraDeIMCTheme`.

## 5. Hierarquia de Composables

A estrutura hierárquica da interface é apresentada abaixo:

```text
MainActivity
└── CalculadoraDeIMCTheme
    └── Surface
        └── CalculadoraimcLayout
            ├── Text: Título "Calcular IMC"
            ├── EditNumberField: Peso (kg)
            ├── EditNumberField: Altura (m)
            ├── ArredondarRow
            │   ├── Text: "Arredondar resultado"
            │   └── Switch
            ├── Button: "Calcular"
            │   └── Text
            ├── Spacer
            ├── Text: Resultado do IMC
            └── Text: Classificação
                (exibido quando há classificação)
```

A função `CalculadoraIMCPreview()` utiliza o tema e chama `CalculadoraimcLayout()` para apresentar a prévia da tela no editor do Android Studio.

As funções `calcularIMC()` e `obterClassificacao()`, assim como o objeto `LimitesIMC`, são independentes da hierarquia visual e fornecem a lógica necessária para o cálculo e a classificação do resultado.

## 6. Identificação do Aluno

- **Aluno:** Leandro Otávio de Almeida
- **Curso:** Técnico de Informática
- **Turma:** 3 período 2026


