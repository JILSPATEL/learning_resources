# Transformer Architecture – Complete Beginner-Friendly Explanation

The **Transformer architecture** is one of the most important architectures in Deep Learning and Natural Language Processing (NLP). It was introduced in the research paper **"Attention Is All You Need" (2017)** by Google researchers.

Transformers are the foundation of many modern AI models, including:

- BERT
- GPT
- T5
- Gemini

Before understanding the architecture, let's establish one important idea:

**A Transformer converts an input sequence of words into meaningful numerical representations, understands the relationships between those words, and then uses that information to generate an output sequence.**

We will understand every component, every arrow, and the complete flow of information.

## Contents

- [1. What problem does the Transformer solve?](#1-what-problem-does-the-transformer-solve)
- [2. Overall Transformer Architecture](#2-overall-transformer-architecture)
- [3. Understanding the Encoder Architecture](#3-understanding-the-encoder-architecture)
- [4. Understanding the Decoder Architecture](#4-understanding-the-decoder-architecture)
- [5. How the Decoder Generates the Final Output](#5-how-the-decoder-generates-the-final-output)
- [6. Complete Transformer Architecture](#6-complete-transformer-architecture--understanding-all-arrows)
- [7. Encoder vs. Decoder](#7-encoder-vs-decoder--complete-comparison)
- [8. Numerical Details of the Original Transformer](#8-important-numerical-details-of-the-original-transformer)
- [9. How Transformer Training Works](#9-how-transformer-training-works)
- [10. Important Concepts to Remember](#10-important-transformer-concepts-you-must-remember)
- [11. Common Interview and Exam Questions](#11-common-interview-and-exam-questions)
- [12. Final Understanding](#12-final-understanding-the-entire-transformer-in-one-example)

# 1. What problem does the Transformer solve?

Suppose we have the following sentence:

> The student reads a book.

A computer does not naturally understand words like humans do. It needs to convert these words into numerical representations.

Traditional NLP architectures, such as RNNs and LSTMs, process words sequentially.

For example:

```
The → student → reads → a → book
```

This creates two major problems:

1. Sequential processing makes parallel computation difficult.
2. Understanding relationships between words that are far apart can be challenging.

The Transformer solves these problems using a mechanism called **Self-Attention**.

Self-attention allows every word to examine other words in the sentence and determine which words are important for understanding its meaning.

For example:

> The animal didn't cross the road because it was tired.

To understand the word "it", the model needs to identify that it refers to "animal".

Self-attention helps the model learn such relationships.

# 2. Overall Transformer Architecture

First, let's understand the complete architecture before studying its individual components.

The original Transformer consists of two major blocks:

1. Encoder
2. Decoder

![Masked Multi Head Attention in Transformer | by Sachin Soni | Medium](https://images.openai.com/static-rsc-4/DhNpD_0SwkJ1UNkAGmEAP9uKbbyKl_8QSCA9BHu1beaL8h1amHeG1LtJdEYO_4xv8MJoq3BfrggJJrtcaAIL9R9FtLqDHY6jrQUDAADHNMA4eUq60ifyTT6S4DOmX1XUlOVot4NuuCYOg-d4etllzQwzyhvWVCSOS0fEV_h4CcY?purpose=inline)

*Source: [medium.com](https://medium.com/%40sachinsoni600517/masked-multi-head-attention-in-transformer-f3e096d56961?utm_source=chatgpt.com)*

The basic responsibility of each block is:

| Component | Responsibility                                                                                |
| --------- | --------------------------------------------------------------------------------------------- |
| Encoder   | Understands and processes the input sentence.                                                 |
| Decoder   | Generates the output sentence using the encoder's information and previously generated words. |

Let's take a translation example:

**Input:**

```
I love machine learning
```

**Expected output:**

```
J'aime l'apprentissage automatique
```

The encoder understands the English sentence.

The decoder uses that understanding to generate the French translation.

An important point: the original Transformer has multiple encoder and decoder layers stacked on top of each other. It does not have just one encoder layer and one decoder layer.

Let's understand each part.

# 3. Understanding the Encoder Architecture

![Transformer Model Architecture With Attention And Feed-forward Blocks テンプレート - FigPad](https://images.openai.com/static-rsc-4/cwDcowoEjQeFUZeMKJdjjmkbOBS7ZM0UQOgzOf1hlWQtDqsyNEe4Ai1ykWvMT-waj3UpGZP09HJpzzcXDLW9I72VHd9LasQDcQIo15NZ1gq6gwsa1zB_7AxqW0o0Gjch7VA1tMFTUeVkfbaww7MNEmdzPJyhdds1hrYuztwVPs0?purpose=inline)

*Source: [figpad.ai](https://figpad.ai/ja/scientific-visualization/transformer-model-architecture-attention-schematic-minimal-variant-2?utm_source=chatgpt.com)*

The encoder is responsible for understanding the input sentence.

It takes the input tokens and produces contextual representations of those tokens.

For example:

```
Input:
The student reads a book.
```

The encoder processes this sentence and learns:

- The meaning of each word.
- The relationship between words.
- Which words are related to one another.
- The context in which each word is used.

The encoder has the following components:

1. Input Embedding
2. Positional Encoding
3. Multi-Head Self-Attention
4. Add & Norm
5. Feed Forward Neural Network
6. Add & Norm

Let's understand them one by one.

## 3.1 Input Embedding

![Guide to Tokenization and Padding with BERT: Transforming Text into Machine-Readable Data | by Piyush Kashyap | Medium](https://images.openai.com/static-rsc-4/NEFXFp5USVlzlFZWv3-VZaDDDvrREPci2TC73zXR16qIbK1oxugimF_w7rpTByUgtGVN6dxKykiY6RBUWyYpdtFwDMSZxQin6G7xr-hkD62nTX3pqbSUcYM85ZkGSMpZvBoBQU_QV8YXSIKkU7HhdsYbtRF4KH3tY9LbhJ3oXN4?purpose=inline)

*Source: [medium.com](https://medium.com/%40piyushkashyap045/guide-to-tokenization-and-padding-with-bert-transforming-text-into-machine-readable-data-5a24bf59d36b?utm_source=chatgpt.com)*

**First question: Why do we need embeddings?**

Computers cannot directly understand words. They perform mathematical operations on numbers.

Therefore, we convert each input token into a numerical vector.

Consider:

```
Input sentence:

The student reads a book.
```

First, tokenization converts the sentence into tokens:

```
["The", "student", "reads", "a", "book"]
```

Each token is mapped to a vector using an embedding layer.

For example, imagine the following simplified vectors:

| Token   | Embedding vector     |
| ------- | -------------------- |
| The     | [0.2, 0.5, 0.1, 0.8] |
| student | [0.4, 0.3, 0.7, 0.2] |
| reads   | [0.6, 0.1, 0.9, 0.3] |
| a       | [0.1, 0.2, 0.4, 0.5] |
| book    | [0.8, 0.6, 0.2, 0.1] |

These are just illustrative values, not actual pretrained embeddings.

Each word is now represented by a vector of numbers.

In the original Transformer, the embedding dimension is:

$$
 d_{\text{model}}=512 
$$

That means each token is represented by a vector containing 512 numbers.

**What is the purpose of Input Embedding?**

To convert words or tokens into numerical vectors that the neural network can process.

**Arrow meaning:**

```
Input Tokens
     |
     | Token IDs are converted into vectors
     v
Input Embeddings
```

The arrow indicates that the output of the embedding layer becomes the input to the next component.

## 3.2 Positional Encoding

![Karpathy Makes GPT from Scratch | NextBigFuture.com](https://images.openai.com/static-rsc-4/eocbvAdmvudpvpTO7qfQ0QgcnFS8g7-FN_JWjEANy8nwGgPbKTAfM34iy7HZCQdqUTfRR9BIDtkucskY7M8AbtPU-7AskHLutDFI2iObaA9veJW5PyIiaTyog2AXaxVZpD_yDajwvzySBGe1FD1pyDXg3nOLN_bq97TbOKEftQc?purpose=inline)

*Source: [nextbigfuture.com](https://www.nextbigfuture.com/2023/04/karpathy-makes-gpt-from-scratch.html?utm_source=chatgpt.com)*

This is one of the most important concepts in Transformer architecture.

**Problem:**

Unlike RNNs, Transformers do not naturally process words one after another.

They process tokens in parallel.

Therefore, the model needs some way to understand the position of each word in a sentence.

Consider:

```
Sentence 1:
The dog bites the man.

Sentence 2:
The man bites the dog.
```

Both sentences contain similar words, but their meanings are different because the word order changes.

How does the Transformer understand word positions?

Using **Positional Encoding**.

Positional encoding adds information about the position of each token to its embedding vector.

For example:

| Word    | Position |
| ------- | -------- |
| The     | 0        |
| student | 1        |
| reads   | 2        |
| a       | 3        |
| book    | 4        |

Each position gets a positional vector.

The Transformer adds the positional vector to the word embedding.

$$
 X = E + PE 
$$

Where:

- $E$ = Word Embedding
- $PE$ = Positional Encoding
- $X$ = Final representation containing word and position information

For example:

```
Word embedding:       [0.2, 0.5, 0.1, 0.8]

Position embedding:   [0.1, 0.3, 0.2, 0.4]

                      ---------------------

Final representation: [0.3, 0.8, 0.3, 1.2]
```

The numbers are illustrative.

**How is positional encoding calculated in the original Transformer?**

The original paper uses sinusoidal functions:

$$
 PE(pos,2i)= \sin\left(\frac{pos}{10000^{2i/d_{\text{model}}}}\right) 
$$

$$
 PE(pos,2i+1)= \cos\left(\frac{pos}{10000^{2i/d_{\text{model}}}}\right) 
$$

Here:

- $pos$ = Position of the token.
- $i$ = Dimension index.
- $d_{\text{model}}$ = Embedding dimension.

You don't need to memorize the mathematical formula initially. Understand that it creates a unique positional representation for each position.

**Arrow meaning:**

```
Input Embedding --------\
                         + ----> Combined Representation
Positional Encoding ----/
```

The two arrows indicate that both vectors are combined through element-wise addition.

The resulting representation contains:

1. Information about the word.
2. Information about the word's position.

## 3.3 Multi-Head Self-Attention

![Transformers are arguably the most impactful deep learning architecture from the last 5 yrs.  In the... - Misha Laskin | Rattibha](https://images.openai.com/static-rsc-4/Zr6Ra06aM9nrWxDOeF3lFAKpcyATf2sZ062jTla9sMSQhTyZGA-prGryvdIbbDBtzWssO-3Z0CM2R5u7UQhe_RuolyCawCZLE4opwy-UsS9IDCiW_pnk6VnbaDZNc2_aWtNT06qRAAzVRaOnSg09Psn1to-vD2nzPS6-Se_6i1E?purpose=inline)

*Source: [en.rattibha.com](https://en.rattibha.com/thread/1479246928454037508?utm_source=chatgpt.com)*

> **Key idea:** Self-attention is the central mechanism in a Transformer.


Now we reach the most important part of the Transformer.

First, understand the word **Attention**.

Attention means determining which other words are important when understanding a particular word.

Consider:

```
The animal crossed the road because it was tired.
```

When understanding "it", the model should pay more attention to "animal".

Self-attention helps the model establish these relationships.

But how does it work mathematically?

It uses three vectors:

- Query (Q)
- Key (K)
- Value (V)

Let's understand each one.

### A. Query (Q)

Query represents:

**What information am I looking for?**

Imagine you are searching for information about a particular word.

The query represents what that word is looking for from the other words.

### B. Key (K)

Key represents:

**What information do I contain?**

Every word has a key vector.

The model compares a query with the keys of other words to determine how relevant they are.

### C. Value (V)

Value represents:

**What information should I contribute?**

Once the model identifies relevant words, it uses their value vectors to collect information.

Think of it like a search engine:

| Attention concept | Search engine analogy                |
| ----------------- | ------------------------------------ |
| Query             | What you search for                  |
| Key               | Information used to match the search |
| Value             | Actual information retrieved         |

This analogy helps understand the purpose of Q, K and V.

### How are Q, K and V generated?

The input representation is multiplied by three different trainable weight matrices.

$$
 Q=XW_Q 
$$

$$
 K=XW_K 
$$

$$
 V=XW_V 
$$

Where:

- $X$ = Input representation.
- $W_Q$ = Query weight matrix.
- $W_K$ = Key weight matrix.
- $W_V$ = Value weight matrix.

These weight matrices are learned during model training.

The model creates Q, K and V for every token.

### D. Scaled Dot-Product Attention

![Transformer: Attention Is All You Need – ∇ ickma.dev](https://images.openai.com/static-rsc-4/5MUYw62i2YcdXjkYrnQ1ZzA0Yg4M4TQOnGO90qzQOBI4sKEWWmofWF8rfK92CaJd_gc6cRdBGf3jlrYiMUy7niyEv9Itsxh0sCE_WBzRtIpvJ9DtiLfPYdVjKuKSj9dX9VYSOw3GKJIE0nX1hGp2jZcfldNX1OUcIlleyKmnuGs?purpose=inline)

*Source: [ickma2311.github.io](https://ickma2311.github.io/ML/papers/transformer-attention-is-all-you-need.html?utm_source=chatgpt.com)*

Now we need to determine how much attention one word should give to other words.

The Transformer calculates the similarity between Query and Key vectors using a dot product.

The formula is:

$$
 Attention(Q,K,V)= softmax\left( \frac{QK^T}{\sqrt{d_k}} \right)V 
$$

Let's break this formula into four steps.

**Step 1: Calculate similarity**

$$
 QK^T 
$$

The model compares the query of a word with the keys of all words.

This produces attention scores.

Higher scores generally indicate greater relevance.

**Step 2: Scaling**

$$
 \frac{QK^T}{\sqrt{d_k}} 
$$

Here, $d_k$ is the dimension of the key vectors.

Why divide by $\sqrt{d_k}$?

When vector dimensions become large, dot products can become very large. This can make the softmax function produce extremely concentrated probabilities and cause difficult gradients during training.

Scaling helps maintain stable values.

**Step 3: Apply Softmax**

$$
 softmax(scores) 
$$

Softmax converts the scores into probability-like attention weights that sum to 1.

Example:

| Word    | Attention weight |
| ------- | ---------------- |
| The     | 0.05             |
| animal  | 0.65             |
| crossed | 0.10             |
| road    | 0.15             |
| tired   | 0.05             |

These are hypothetical weights.

The model gives more attention to "animal" in this example.

**Step 4: Multiply by Values**

$$
 AttentionWeights \times V 
$$

The model multiplies each word's value vector by its attention weight and combines the results.

Therefore, the final representation contains information from other relevant words.

**Complete flow:**

```
                 Input X
                    |
          ---------------------
          |         |         |
          v         v         v
          Q         K         V
          |         |         |
          |         v         |
          |      K transpose  |
          |         |         |
          -----> Q × Kᵀ       |
                    |         |
                    v         |
                  Scaling     |
                    |         |
                    v         |
                  Softmax     |
                    |         |
                    v         |
              Attention Weights
                    |         |
                    ------× V--
                         |
                         v
                  Attention Output
```

**Meaning of arrows:**

- Input to Q, K and V: creates three different projections of the same input.
- Q and K: calculate similarity scores.
- Scores to Softmax: convert scores into normalized attention weights.
- Softmax to multiplication with V: determine how much information to collect from each token.
- Final arrow: produces the contextual representation.

### E. Multi-Head Attention

You might now ask:

Why is it called Multi-Head Attention instead of just Self-Attention?

Because the Transformer uses multiple attention heads in parallel.

Imagine this sentence:

```
The student reads a book in the library.
```

Different attention heads can learn different types of relationships:

- Head 1: Relationships between subject and action.
- Head 2: Relationships between actions and objects.
- Head 3: Relationships involving locations.
- Head 4: Other grammatical or semantic relationships.

These are intuitive examples of what attention heads may learn, not fixed assignments.

In the original Transformer:

$$
 h=8 
$$

There are eight attention heads.

Each head independently performs scaled dot-product attention.

The outputs of all heads are concatenated.

$$
 MultiHead(Q,K,V)= Concat(head_1,\ldots,head_h)W_O 
$$

Where:

- $h$ = Number of heads.
- $W_O$ = Output projection matrix.
- $Concat$ = Concatenation of all head outputs.

For the original Transformer:

$$
 d_{\text{model}}=512 
$$

$$
 d_k=d_v=\frac{512}{8}=64 
$$

Each head works with a 64-dimensional representation.

**Why multiple heads?**

Because different attention heads can learn different relationships simultaneously, giving the model a richer understanding of the sentence.

## 3.4 Add & Norm (Residual Connection + Layer Normalization)

![Attention 기법 | DataLatte's IT Blog](https://images.openai.com/static-rsc-4/MHchYrzBny6R351ZkuVul27Razo2R2CZUgoaDSeATMn3SJOcQ5bOedcDsImYFZKw9mgFpRhF3uCOkoHKKhkUN-de-fkDapx8BBMPU5uemFt7PZ4_am6L64AamcGiDMVGqUO6yZu7b5jW2HpAFWxDQlmyysrF-lK5YADRmS7rA0M?purpose=inline)

*Source: [heung-bae-lee.github.io](https://heung-bae-lee.github.io/2020/01/21/deep_learning_10/?utm_source=chatgpt.com)*

After Multi-Head Attention, we see a block called:

**Add & Norm**

It contains two operations:

1. Residual Connection (Add)
2. Layer Normalization (Norm)

Let's understand both.

### A. Residual Connection (Add)

Suppose the input to the attention layer is $X$.

The attention layer produces an output:

$$
 Attention(X) 
$$

Now the Transformer adds the original input to the attention output.

$$
 Output=X+Attention(X) 
$$

Why do we do this?

As neural networks become deeper, training them can become difficult because information and gradients may be lost or weakened through many layers.

Residual connections provide a direct path for information and gradients.

This helps the network train more effectively.

Look at the arrows:

```
                  Input X
                  /     \
                 /       \
                v         |
        Multi-Head        |
        Attention         |
                |         |
                v         |
             Attention   |
               Output    |
                |         |
                v         v
                 Add <----
                  |
                  v
             Layer Norm
                  |
                  v
              Final Output
```

**What does the bypass arrow mean?**

This is extremely important.

The arrow that goes around the Multi-Head Attention block is called a **skip connection** or **residual connection**.

It carries the original input directly to the addition operation.

The other arrow carries the attention output.

Both are added together.

### B. Layer Normalization

After addition, the Transformer applies Layer Normalization.

Its purpose is to normalize the activations of the neural network across the feature dimensions for each token.

It helps make training more stable.

A simplified representation is:

$$
 LayerNorm(X)= \gamma\frac{X-\mu}{\sqrt{\sigma^2+\epsilon}}+\beta 
$$

Where:

- $\mu$ = Mean.
- $\sigma^2$ = Variance.
- $\gamma$ = Learnable scaling parameter.
- $\beta$ = Learnable shifting parameter.
- $\epsilon$ = Small constant for numerical stability.

The output of Add & Norm becomes the input to the next component.

## 3.5 Feed Forward Neural Network (FFN)

![Attention Is All You Need: A Deep Dive into Transformer Architecture | by Rezowanur Rahman Robin | Medium](https://images.openai.com/static-rsc-4/243uI3kkkO_A_QuL-UQbNzWWyx-cRjWwRSSeOxkco4qTuDu7YxEyx98InNNIKnbNATbLx8WZljepYH1c5kjSPjG32i5qaAzIzQtTiQo2vvAaabDaD532rTu3CHxRSzbCWEa2zEr2VgE7oUX-UL4679fNMqBMrXEkPZxoxMQ83LU?purpose=inline)

*Source: [medium.com](https://medium.com/%40robin5002234/attention-is-all-you-need-a-deep-dive-into-transformer-architecture-8c34753098c7?utm_source=chatgpt.com)*

After self-attention and Add & Norm, the output passes through the Feed Forward Neural Network.

You might ask:

If attention already understands the relationships between words, why do we need another neural network?

Because attention focuses on combining information across tokens.

The Feed Forward Network transforms the resulting representation at each token position.

The original Transformer uses two linear layers with a ReLU activation function between them.

$$
 FFN(x)=max(0,xW_1+b_1)W_2+b_2 
$$

Let's understand its operations.

**Step 1: First Linear Layer**

Transforms the input into a higher-dimensional representation.

In the original Transformer:

$$
 512 \rightarrow 2048 
$$

**Step 2: ReLU Activation**

ReLU introduces non-linearity.

$$
 ReLU(x)=max(0,x) 
$$

It replaces negative values with zero and keeps positive values unchanged.

**Step 3: Second Linear Layer**

Projects the representation back to its original dimension.

$$
 2048 \rightarrow 512 
$$

Complete flow:

```
Input: 512 dimensions
          |
          v
    Linear Layer 1
          |
          v
   2048 dimensions
          |
          v
       ReLU
          |
          v
    Linear Layer 2
          |
          v
    512 dimensions
          |
          v
       Output
```

**Important:** The same Feed Forward Network is applied independently to each token position. Its weights are shared across positions.

## 3.6 Second Add & Norm

After the Feed Forward Network, another Add & Norm block appears.

It performs the same two operations:

1. Adds the FFN output to the original input of that FFN block.
2. Applies Layer Normalization.

The equation is:

$$
 Output=LayerNorm(X+FFN(X)) 
$$

Here, $X$ represents the input to the Feed Forward Network.

Now one complete encoder layer is finished!

### Complete Encoder Layer

![Medium](https://images.openai.com/static-rsc-4/W0njn93N_eT8hC3eguqV6uXApSQrtMIOKu0RHN9M0P54o2ekUv7jUxJy02QZcPCBSljHp8r4AUgUEKIyf7tCwJDEB-irKGjI35rR_OIpQEyCBfzHm1-ODjyZTLD-jICIYFb3N1wop6IlqDIFwNpT3uBV_7WCyy06cIuTqOTsLkQ?purpose=inline)

*Source: [naokishibuya.medium.com](https://naokishibuya.medium.com/transformers-encoder-decoder-434603d19e1?utm_source=chatgpt.com)*

Let's summarize:

| Step | Component                 | Purpose                                         |
| ---- | ------------------------- | ----------------------------------------------- |
| 1    | Multi-Head Self-Attention | Understands relationships between words.        |
| 2    | Add & Norm                | Adds original input and normalizes.             |
| 3    | Feed Forward Network      | Transforms each token representation.           |
| 4    | Add & Norm                | Adds residual information and normalizes again. |

The original Transformer stacks **6 encoder layers**.

The output of encoder layer 1 becomes the input to encoder layer 2, and so on.

```
Input Embeddings + Positional Encoding
                 |
                 v
          Encoder Layer 1
                 |
                 v
          Encoder Layer 2
                 |
                 v
          Encoder Layer 3
                 |
                 v
          Encoder Layer 4
                 |
                 v
          Encoder Layer 5
                 |
                 v
          Encoder Layer 6
                 |
                 v
          Encoder Output
```

The final encoder output contains contextual information about all input tokens.

This output is passed to the decoder.

# 4. Understanding the Decoder Architecture

![Understanding Transformers architecture with Pytorch code | by Ashish bisht | Medium](https://images.openai.com/static-rsc-4/kbP9JgEmOv2ETDKGhy1RsmXS6oX13TPrJBI93E39E_Oe-NuNvb4KiCcLtueDRmR7xHda3PVgZDo5iJrdZx7HtqtCFrHtvRjC0QDugTvujHuVyVs54-YHogRYwNC1Y_veWPaQplSOxsOXOeoYmngKvpoqfVjxreEEjT0pajrGKH4?purpose=inline)

*Source: [medium.com](https://medium.com/%40ashishbisht0307/understanding-transformers-architecture-with-pytorch-code-c422c5fb1cd2?utm_source=chatgpt.com)*

The encoder understands the input sentence.

The decoder uses the encoder's output to generate the target sentence.

For example:

```
Input:
I love machine learning.

Encoder:
Understands the input sentence.

Decoder:
Generates the French translation.

Output:
J'aime l'apprentissage automatique.
```

The decoder contains the following components:

1. Output Embedding
2. Positional Encoding
3. Masked Multi-Head Self-Attention
4. Add & Norm
5. Encoder-Decoder Attention (Cross-Attention)
6. Add & Norm
7. Feed Forward Neural Network
8. Add & Norm
9. Linear Layer
10. Softmax

Let's understand them one by one.

## 4.1 Output Embedding

The decoder also needs numerical representations of words.

But there is one important difference.

The encoder receives the original input sentence.

The decoder receives the target sequence generated so far, shifted right during training.

Consider the expected output:

```
I love machine learning.
```

The decoder input during training might be:

```
<BOS> I love machine
```

Here:

- BOS = Beginning of Sequence.
- The decoder receives previously known target tokens.
- It learns to predict the next token.

For example:

| Decoder input      | Expected prediction |
| ------------------ | ------------------- |
| BOS                | I                   |
| BOS I              | love                |
| BOS I love         | machine             |
| BOS I love machine | learning            |

These tokens are converted into numerical vectors using an output embedding layer.

**Arrow meaning:**

```
Previous Output Tokens
          |
          v
    Output Embedding
          |
          v
   Numerical Vectors
```

## 4.2 Positional Encoding

Just like the encoder, the decoder also needs positional information.

It adds positional encoding to the output embeddings.

$$
 X=Embedding+PositionalEncoding 
$$

This helps the decoder understand the order of previously generated words.

## 4.3 Masked Multi-Head Self-Attention

![Encoder-Decoder Architecture: Cross-Attention & Sequence-to-Sequence Transformers - Interactive | Michael Brenndoerfer | Michael Brenndoerfer](https://images.openai.com/static-rsc-4/XMuf6lYbGIAIEUV6Fg-mrV1DpY6aMSaOHvrzDrfQJFeA1nsqmvcAYlxFuRdxGcZBv8OXLLSbJftSmoxHypdyN-Ppn6qtEa_Nkrp0u2KHM4gR2xujgjTvnE7rXde201CRBb5A-KAzq7DjvdQIpN5btiAJ6wqpRgV-XIpS4WP3DA0?purpose=inline)

*Source: [mbrenndoerfer.com](https://mbrenndoerfer.com/writing/encoder-decoder-architecture-cross-attention-transformers?utm_source=chatgpt.com)*

This is another extremely important concept.

The decoder generates output one token at a time.

Suppose the target sentence is:

```
I love machine learning.
```

When predicting "machine", the decoder should only see:

```
I love
```

It should not see:

```
learning
```

Why?

Because during generation, future words are not yet available.

To enforce this rule, the Transformer uses **Masked Self-Attention**, also called Causal Self-Attention.

### What is masking?

Masking means preventing a token from attending to future tokens.

Consider this attention matrix:

**Causal attention mask**

Each row represents the current token's attention. Each column represents a token position.

|          | I       | love    | machine | learning |
| -------- | ------- | ------- | ------- | -------- |
| I        | Allowed | Masked  | Masked  | Masked   |
| love     | Allowed | Allowed | Masked  | Masked   |
| machine  | Allowed | Allowed | Allowed | Masked   |
| learning | Allowed | Allowed | Allowed | Allowed  |

✓ = The token can attend to this position. × = The token cannot attend to this future position.

For example:

- "I" can only see "I".
- "love" can see "I" and "love".
- "machine" can see "I", "love" and "machine".
- "learning" can see all four positions.

Technically, the future positions are masked by adding a very large negative value (effectively $-\infty$) to their attention scores before applying Softmax.

This makes their attention weights zero.

**Why do we need this?**

To prevent the decoder from cheating by looking at future words during training.

It ensures that the model learns to predict the next word using only previous words.

**Arrow meaning:**

```
Previous Output Tokens
          |
          v
     Q, K, V
          |
          v
  Causal Mask Applied
          |
          v
      Softmax
          |
          v
  Masked Self-Attention
          |
          v
       Output
```

The masking operation blocks information flowing from future positions.

## 4.4 Add & Norm

Just like the encoder, the decoder has a residual connection and Layer Normalization after masked self-attention.

$$
 Output=LayerNorm(X+MaskedAttention(X)) 
$$

The skip connection carries the original input directly to the addition operation.

This helps preserve information and supports stable training.

## 4.5 Encoder-Decoder Attention (Cross-Attention)

> **Key idea:** Cross-attention connects the decoder to the encoder output.


![An intelligent framework combining deep learning and fuzzy logic for accurate remote language translation | Scientific Reports](https://images.openai.com/static-rsc-4/d2BCPHcee8cDNxwDH1zrghnFKN7-vdOsEdYqooTCqLePSjIJ-bP-aXvWw-8hW8F9hCXR-Kzb43C98_LALwHtB32eI963M3xYxJyj_SqiJHyTkLH3qpKogXLf6izktNMyv9Et9kHR5ewmHWIxxulxmiEZPJ5Y2icuKwe0ArRB_4M?purpose=inline)

*Source: [nature.com](https://www.nature.com/articles/s41598-025-22549-3?utm_source=chatgpt.com)*

This is the component that connects the encoder and decoder.

It is also called **Cross-Attention**.

First, understand the situation.

The encoder has already processed the input sentence:

```
I love machine learning.
```

It produces contextual representations of the input words.

The decoder has started generating the target sentence.

Now the decoder needs to look at the encoder's output to understand which input information is relevant for generating the next word.

Cross-attention makes this possible.

### How does Cross-Attention work?

Here is the most important concept:

- Query (Q) comes from the decoder.
- Key (K) comes from the encoder.
- Value (V) comes from the encoder.

Remember this relationship!

$$
 Q=DecoderOutput \times W_Q 
$$

$$
 K=EncoderOutput \times W_K 
$$

$$
 V=EncoderOutput \times W_V 
$$

Now the decoder's Query is compared with the encoder's Keys.

The attention mechanism determines which input words are relevant to the current output position.

Then it uses the encoder's Values to collect information.

The formula is:

$$
 CrossAttention(Q,K,V)= softmax\left( \frac{QK^T}{\sqrt{d_k}} \right)V 
$$

### Example

Suppose:

```
English:
I love machine learning.

French:
J'aime l'apprentissage automatique.
```

When generating the French word "J'aime", the decoder can use relevant information from the English words "I love".

When generating "apprentissage", it can use information related to "learning".

These are illustrative alignments; actual attention can distribute across multiple input tokens.

### Understand the arrows carefully

```
                  ENCODER OUTPUT
                   /          \
                  /            \
                 v              v
               KEY (K)        VALUE (V)
                  \              /
                   \            /
                    \          /
                     CROSS ATTENTION
                           ^
                           |
                       QUERY (Q)
                           ^
                           |
                    DECODER OUTPUT
```

The arrows mean:

1. Encoder output is sent to the Key projection.
2. Encoder output is also sent to the Value projection.
3. Decoder output is sent to the Query projection.
4. Query and Key are compared to calculate attention scores.
5. The attention weights are applied to the encoder's Values.
6. The resulting contextual information is returned to the decoder.

**Most important difference:**

| Self-Attention                                      | Cross-Attention                                                |
| --------------------------------------------------- | -------------------------------------------------------------- |
| Q, K and V come from the same sequence.             | Q comes from decoder; K and V come from encoder.               |
| Finds relationships between tokens in one sequence. | Finds relationships between decoder tokens and encoder tokens. |
| Used in encoder and decoder.                        | Used in the original encoder-decoder architecture's decoder.   |

This is how the decoder uses the encoder's understanding of the input sentence.

## 4.6 Second Add & Norm

After Cross-Attention, the decoder uses another Add & Norm block.

It:

1. Adds the Cross-Attention output to its original input.
2. Applies Layer Normalization.

$$
 Output=LayerNorm(X+CrossAttention(X)) 
$$

Here, the residual input is the representation entering the cross-attention sublayer.

## 4.7 Feed Forward Neural Network

Now the output passes through the Feed Forward Neural Network.

It performs the same operations as the encoder's FFN:

```
Input
  |
  v
Linear Layer 1
  |
  v
ReLU
  |
  v
Linear Layer 2
  |
  v
Output
```

The original Transformer uses:

$$
 512 \rightarrow 2048 \rightarrow 512 
$$

It transforms each token representation independently.

## 4.8 Third Add & Norm

After the Feed Forward Network, another Add & Norm block is applied.

$$
 Output=LayerNorm(X+FFN(X)) 
$$

Now one complete decoder layer is finished.

The original Transformer stacks six decoder layers.

# 5. How the Decoder Generates the Final Output

We have now understood the internal decoder layers.

But how does the decoder convert its output into actual words?

This is done using:

1. Linear Layer
2. Softmax

![The Illustrated Transformer – Jay Alammar – Visualizing machine learning one concept at a time.](https://images.openai.com/static-rsc-4/DdFU_dBS-PDsZgcrKxBO0luPIdCsgqLP_iApYbt9YJdQvhDAfuncuPcVT4aUo_7L5AHwmCA-LU6oJNKQMLYEjrSv-TFLkn466ZpN3Q4assutnzB9xUxZCXo5E8Odz6Rd_2CqKN-Avy7VQhI_32BCay1g_nqGv1sJ7tw7qmeoYfY?purpose=inline)

*Source: [jalammar.github.io](https://jalammar.github.io/illustrated-transformer/?utm_source=chatgpt.com)*

## 5.1 Linear Layer

The decoder's final hidden representation is passed through a linear layer.

This layer maps the hidden representation to a vector whose size is equal to the vocabulary size.

For example:

Suppose the vocabulary contains 10,000 tokens.

The output vector will contain 10,000 scores.

Each score corresponds to a possible vocabulary token.

## 5.2 Softmax

The scores are passed through Softmax.

Softmax converts them into probabilities.

Example:

| Possible next word | Probability |
| ------------------ | ----------- |
| machine            | 0.65        |
| learning           | 0.10        |
| computer           | 0.15        |
| science            | 0.10        |

The model selects a next token according to its decoding strategy.

It may use greedy selection, beam search, or sampling.

For example, if it selects "machine", the word is added to the decoder input.

## 5.3 Autoregressive Generation

This is how the decoder generates a sentence step by step.

Suppose the expected output is:

```
I love machine learning.
```

The process looks like this:

**Step-by-step generation**

1. **Step 1: Start token**

   Input

   `<BOS>`

   Decoder predicts: I
2. **Step 2: First word generated**

   `<BOS> I`

   Decoder predicts: love
3. **Step 3: Second word generated**

   `<BOS> I love`

   Decoder predicts: machine
4. **Step 4: Third word generated**

   `<BOS> I love machine`

   Decoder predicts: learning
5. **Step 5: Final word generated**

   `<BOS> I love machine learning`

   Decoder predicts: EOS

Final output

**I love machine learning.**

Here, EOS means End of Sequence.

The process continues until the model generates an EOS token or reaches a maximum output length.

This process is called **Autoregressive Generation** because each newly generated token becomes part of the input for generating the next token.

# 6. Complete Transformer Architecture – Understanding All Arrows

Now that we understand every component, let's connect everything.

![Medium](https://images.openai.com/static-rsc-4/0323UkLuAkXviYLcYQ05i-5xZ5md3NAkP3iGmqKMn7vVGwGHcAgRUB0L49pMmLdxUsgQCkVCy6iDoOWs2hK3iYHp2Ezol7RbVlEcRgH_i_SuVskoB6MzuKY7d-qStwiyCdxFdPukv4wi2rA49Sn9G1_GZRn59u2J3fhn9buK6jA?purpose=inline)

*Source: [medium.com](https://medium.com/%40sharifghafforov00/transformer-models-no-need-for-expensice-set-ups-anymore-c579b357e9b2?utm_source=chatgpt.com)*

This architecture diagram may initially look complicated, but now you know what each block means.

Let's understand the arrows systematically.

## 6.1 Encoder arrows

| Arrow                                      | Meaning                                                                             |
| ------------------------------------------ | ----------------------------------------------------------------------------------- |
| Input → Input Embedding                    | Converts token IDs into numerical vectors.                                          |
| Input Embedding → Positional Encoding      | Adds information about token positions.                                             |
| Positional Encoding → Multi-Head Attention | Sends combined word and position information into self-attention.                   |
| Attention → Add & Norm                     | Sends contextual attention output to the residual addition and normalization block. |
| Skip arrow around Attention                | Carries the original input directly to Add & Norm.                                  |
| Add & Norm → Feed Forward                  | Sends normalized representations to the FFN.                                        |
| FFN → Add & Norm                           | Sends transformed representations for residual addition and normalization.          |
| Skip arrow around FFN                      | Carries the FFN's original input directly to Add & Norm.                            |
| Encoder Layer → Next Encoder Layer         | Passes contextual representations to the next encoder block.                        |
| Final Encoder → Decoder Cross-Attention    | Supplies the encoder's Key and Value information to the decoder.                    |

## 6.2 Decoder arrows

| Arrow                                  | Meaning                                                                        |
| -------------------------------------- | ------------------------------------------------------------------------------ |
| Target Tokens → Output Embedding       | Converts previous target tokens into vectors.                                  |
| Output Embedding → Positional Encoding | Adds position information.                                                     |
| Positional Encoding → Masked Attention | Sends token representations into causal self-attention.                        |
| Masking → Attention                    | Prevents access to future tokens.                                              |
| Masked Attention → Add & Norm          | Sends attention output to residual addition and normalization.                 |
| Add & Norm → Cross-Attention           | Supplies the decoder representation to the next attention block.               |
| Decoder → Query                        | Generates the Query vectors for cross-attention.                               |
| Encoder → Key and Value                | Provides the source information for cross-attention.                           |
| Cross-Attention → Add & Norm           | Combines the cross-attention output with its residual input and normalizes it. |
| Add & Norm → FFN                       | Passes representations into the feed-forward network.                          |
| FFN → Add & Norm                       | Sends transformed representations to the final residual block.                 |
| Final Decoder → Linear                 | Maps hidden representations to vocabulary scores.                              |
| Linear → Softmax                       | Converts vocabulary scores into probabilities.                                 |
| Softmax → Output                       | Produces the next token.                                                       |

## 6.3 Important: What do the different arrow types mean?

In the original architecture, arrows generally indicate the direction in which information or data flows.

There are a few special cases:

- **Normal arrows:** Carry the output of one operation to another.
- **Branching arrows:** Send the same information to multiple operations, such as generating Q, K and V.
- **Skip arrows:** Bypass an operation and carry the original input to an addition operation.
- **Encoder-to-decoder arrows:** Transfer encoder information into decoder cross-attention.
- **Repeated layer arrows:** Pass the output of one layer into the next layer.
- **Feedback during generation:** Previously generated tokens are supplied back as input to predict the next token.

An arrow does not necessarily mean addition or multiplication. The operation depends on the block where the arrow ends.

# 7. Encoder vs Decoder – Complete Comparison

This is an important topic for exams and interviews.

| Feature              | Encoder                    | Decoder                                                     |
| -------------------- | -------------------------- | ----------------------------------------------------------- |
| Main purpose         | Understands input          | Generates output                                            |
| Input                | Source sequence            | Previously generated target sequence                        |
| Embedding            | Input embedding            | Output embedding                                            |
| Positional encoding  | Yes                        | Yes                                                         |
| First attention      | Multi-Head Self-Attention  | Masked Multi-Head Self-Attention                            |
| Masking              | Padding mask may be used   | Causal mask and potentially padding mask                    |
| Cross-attention      | No                         | Yes                                                         |
| Feed Forward Network | Yes                        | Yes                                                         |
| Add & Norm           | Two per layer              | Three per layer                                             |
| Final output         | Contextual representations | Vocabulary probability distribution after output projection |

# 8. Important Numerical Details of the Original Transformer

The original Transformer architecture used the following configurations:

| Parameter                     | Original Transformer         |
| ----------------------------- | ---------------------------- |
| Encoder layers                | 6                            |
| Decoder layers                | 6                            |
| Model dimension               | 512                          |
| Attention heads               | 8                            |
| Key dimension per head        | 64                           |
| Value dimension per head      | 64                           |
| Feed Forward hidden dimension | 2048                         |
| Attention mechanism           | Scaled Dot-Product Attention |
| Activation function           | ReLU                         |
| Positional encoding           | Sinusoidal                   |

These are the configurations of the original Transformer, not universal requirements for every modern Transformer.

# 9. How Transformer Training Works

Understanding the architecture is not enough. We should also understand how the model learns.

Let's consider a translation task.

**Input:**

```
I love machine learning.
```

**Expected output:**

```
J'aime l'apprentissage automatique.
```

During training:

1. The encoder receives the input sentence.
2. The decoder receives the correct target sentence shifted right.
3. The decoder predicts the next target token at each position.
4. The predicted probabilities are compared with the correct target tokens.
5. A loss function calculates the prediction error.
6. Backpropagation calculates gradients.
7. The optimizer updates the model's parameters.

The commonly used loss function for this task is Cross-Entropy Loss.

$$
 L=-\sum_{i=1}^{N}y_i\log(\hat y_i) 
$$

Where:

- $y_i$ = Actual target distribution.
- $\hat y_i$ = Predicted probability distribution.

The model tries to minimize this loss during training.

An important distinction:

**Training uses teacher forcing:** the decoder receives the correct previous target tokens.

**Inference uses generated tokens:** the decoder receives its own previously generated tokens.

# 10. Important Transformer Concepts You Must Remember

Let's quickly revise the complete architecture.

## Encoder – Understands the input

**Input Sentence**

**Input Embedding + Positional Encoding**

**Encoder Layer × 6**

Multi-Head Self-Attention

Add & Norm

Feed Forward Network

Add & Norm

**Encoder Output**

## Decoder – Generates the output

**Previous Target Tokens**

**Output Embedding + Positional Encoding**

**Decoder Layer × 6**

Masked Multi-Head Self-Attention

Add & Norm

**Cross-Attention**

Receives K and V from Encoder Output

Add & Norm

Feed Forward Network

Add & Norm

**Linear Layer + Softmax**

**Predicted Next Token**

During autoregressive generation, the predicted token is fed back as part of the next decoder input.

# 11. Common Interview and Exam Questions

Here are some questions you should be able to answer after studying this architecture.

Use the following questions to check your understanding:

**1. Why do Transformers need positional encoding?**

**2. What are Query, Key and Value?**

**3. Why do we use Multi-Head Attention?**

**4. What is the difference between self-attention and cross-attention?**

**5. Why is masking required in the decoder?**

**6. What is the purpose of Add & Norm?**

**7. What does the Feed Forward Network do?**

**8. What is autoregressive generation?**

# 12. Final Understanding: The Entire Transformer in One Example

Let's finish with the simplest possible mental model.

Imagine you are translating:

```
English: I love artificial intelligence.
French:  J'aime l'intelligence artificielle.
```

The complete process:

**Encoder:**

- Converts English words into vectors.
- Adds positional information.
- Uses self-attention to understand relationships between English words.
- Produces contextual representations.

**Decoder:**

- Takes previously generated French tokens.
- Uses masked self-attention to understand the generated portion.
- Uses cross-attention to retrieve relevant information from the encoder.
- Uses its feed-forward network to transform representations.
- Predicts the next French token through Linear + Softmax.

It repeats this process until the complete French sentence is generated.

**Remember these five fundamental ideas:**

1. Embedding converts words into vectors.
2. Positional Encoding tells the model where words occur.
3. Self-Attention determines relationships between words.
4. Cross-Attention connects the decoder with encoder information.
5. Linear + Softmax predicts the next token.

One final distinction worth remembering: the original Transformer has both an encoder and a decoder. BERT primarily uses an encoder-only architecture, while GPT primarily uses a decoder-only architecture. T5 uses an encoder-decoder architecture.

If you understand the flow of information through the encoder, decoder, attention heads, residual connections, and cross-attention, you have understood the core of the Transformer architecture.
