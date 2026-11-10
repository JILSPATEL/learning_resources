# Embeddings, Word2Vec and Positional Encoding in NLP

These three concepts are very important in NLP, especially when understanding how machines convert human language into numerical representations and understand the meaning and order of words.

We will understand them in the following order:

1. Why do we need numerical representation of words?
2. What are embeddings?
3. What is Word2Vec?
4. Types of Word2Vec: CBOW and Skip-Gram.
5. What is positional encoding?
6. How embeddings and positional encoding work together in Transformers.
7. Difference between all three.

# 1. Why Do We Need Numerical Representation of Words?

Computers cannot directly understand human language.

For example:

> "I love machine learning."

Humans understand the meaning of this sentence, but computers work with numerical data.

Therefore, NLP converts words into numerical vectors so that machine learning and deep learning algorithms can process them.

Example:

Consider these words:

- King
- Queen
- Apple
- Mango

We need to represent these words in numerical form.

A simple representation could be:

| Word  | Numerical representation |
| ----- | ------------------------ |
| King  | [1, 0, 0, 0]             |
| Queen | [0, 1, 0, 0]             |
| Apple | [0, 0, 1, 0]             |
| Mango | [0, 0, 0, 1]             |

This is called One-Hot Encoding.

However, it has some limitations:

- It does not capture the meaning of words.
- King and Queen appear completely unrelated.
- It creates sparse vectors.
- It does not capture semantic similarity.

To solve these problems, we use Word Embeddings.

# 2. What Are Embeddings in NLP?

## Definition

An embedding is a technique that converts words, sentences, or other data into dense numerical vectors in a continuous vector space, where similar meanings are represented by vectors that are close to each other.

In simple language:

Embeddings convert words into meaningful numerical representations so that computers can understand relationships between words.

## Example

Consider these words:

- King
- Queen
- Man
- Woman
- Apple

Suppose we represent them using 3-dimensional vectors.

| Word  | Embedding vector |
| ----- | ---------------- |
| King  | [0.8, 0.7, 0.2]  |
| Queen | [0.8, 0.6, 0.3]  |
| Man   | [0.4, 0.8, 0.1]  |
| Woman | [0.4, 0.7, 0.2]  |
| Apple | [0.1, 0.1, 0.9]  |

Notice:

- King and Queen have relatively similar representations.
- Man and Woman have relatively similar representations.
- Apple has a different representation.

These are illustrative vectors, not actual pretrained embeddings.

## Diagram: How Embeddings Work

![Dense vectors capturing meaning](https://images.openai.com/static-rsc-4/7NnF1QxpPI1NbJoGpGTp_m99LbH4YTPnFZ8kSEZfvfEu7sdr1VOZLvSSKbcDLq5pjeT6RZjb9HyDM_ugJTjW0Bs42n9ETc3dOEu80eQq1QFvDxqNbWvsiDDEqBF_RTdm7UR5GMo85jVRONl_r9MeF9IhPj4OvFGec799e3jb6vk?purpose=inline)

*Source: [Dense Vectors: Capturing Meaning with Code | Pinecone](https://www.pinecone.io/learn/series/nlp/dense-vector-embeddings-nlp/)*

The important idea is:

- Similar words have similar vector representations.
- Dissimilar words have different vector representations.

For example:

- Doctor and Physician → Similar embeddings.
- Car and Automobile → Similar embeddings.
- Car and Banana → Different embeddings.

## How are embeddings generated?

Embeddings are generally learned during model training.

For example, a model observes sentences such as:

- The king lives in the palace.
- The queen lives in the palace.
- The man is eating food.
- The woman is eating food.

By learning from word usage and surrounding context, the model develops numerical representations that capture useful linguistic relationships.

## Types of Embeddings

There are different approaches to generating embeddings:

| Type                  | Description                                                                |
| --------------------- | -------------------------------------------------------------------------- |
| Word2Vec              | Learns word representations from surrounding words.                        |
| GloVe                 | Learns word representations using global word co-occurrence statistics.    |
| FastText              | Learns representations using character-level subwords.                     |
| Contextual embeddings | Generate representations depending on the context in which a word appears. |

For example, contextual embeddings can represent the word bank differently in:

- I deposited money in the bank.
- I sat near the river bank.

This is an important difference between traditional Word2Vec embeddings and contextual embeddings.

# 3. What Is Word2Vec?

Important NLP concept

## Definition

Word2Vec is a shallow neural network-based technique used to learn dense vector representations of words by predicting words based on their surrounding context or predicting surrounding words from a given word.

In simple language:

Word2Vec learns the meaning of a word by observing the words that frequently appear around it.

It was introduced by researchers at Google in 2013.

## Example

Consider the sentence:

> "I love machine learning because machine learning is interesting."

Word2Vec analyzes the relationships between words.

For example:

- The word "machine" appears near "learning".
- The word "learning" appears near "machine".
- The word "love" appears near "machine".

By observing these patterns across a large dataset, Word2Vec learns word embeddings.

## Architecture of Word2Vec

Word2Vec has two major architectures:

1. CBOW (Continuous Bag of Words)
2. Skip-Gram

Let's understand both.

# 4. CBOW (Continuous Bag of Words)

## Definition

CBOW predicts the target word using its surrounding context words.

In simple language:

Given surrounding words, predict the missing word.

### Example

Consider this sentence:

> "I love machine learning."

Suppose the target word is `machine`.

The surrounding context words are:

- I
- love
- learning

The model uses these context words to predict the target word.

### Diagram

![CBOW model architecture](https://images.openai.com/static-rsc-4/kuB-3eLr0UVxJilrjCH72Xwvt4Lkua42ET2eeCGjMQF4vTsuChuYe0-wP0RMk26EBc4c7i9XJ9OPovJh-TLewvFxUP2w83Kk25Bdes4yGP_VZQFJBLgCYFRp4NSxAq5L55xUDxeftOIt5tin6H6UfkkciTE234xC5el6mnGH3NY?purpose=inline)

*Source: [CBOW Model: Architecture, Gradients, and Word Embedding Training - Interactive](https://mbrenndoerfer.com/writing/cbow-model-word2vec-word-embeddings)*

### Working of CBOW

1. Take a sentence as input.
2. Select a target word.
3. Identify surrounding context words using a context window.
4. Convert context words into numerical representations.
5. Pass these representations through the neural network.
6. Predict the target word.
7. Calculate prediction error.
8. Update the weights using backpropagation.

### Example with context window

Sentence:

`I love machine learning because it is interesting`

Suppose the context window size is 2 and the target word is `learning`.

| Context words             | Target word |
| ------------------------- | ----------- |
| I, love, machine, because | learning    |

The model learns to predict `learning` from the context.

Remember: CBOW = Context → Target.

# 5. Skip-Gram Architecture

## Definition

Skip-Gram predicts the surrounding context words using a given target word.

In simple language:

Given one word, predict the words that appear around it.

### Example

Sentence:

> "I love machine learning."

Suppose the target word is:

`machine`

The model tries to predict its surrounding words:

- love
- learning

### Diagram

![Word embeddings](https://images.openai.com/static-rsc-4/0J4qiJPvLnqg59ODDUHVZ5flF3Jo16c_snPY4-3JXZ-i8VlDNIVA2JqqkYfmDV8jQWcYlZGenoFaERgw9jfJ7_gUDfwjOOAfQJr6ScEJ9wOHwqNuwfBH6lOzvToNlMEeLToewJUdMlDf2N_jzo4vuOGpXC5nZzSHBQRr_M5LL2c?purpose=inline)

*Source: [Word Embeddings - Complete NLP Series Part 4](https://www.wasilzafar.com/pages/series/nlp/nlp-word-embeddings.html)*

### Working of Skip-Gram

1. Take a sentence as input.
2. Select one target word.
3. Identify surrounding context words.
4. Pass the target word into the neural network.
5. Predict surrounding context words.
6. Calculate prediction error.
7. Update weights using backpropagation.

### Example

Sentence:

`I love machine learning`

If the target word is `machine` and the context window size is 2:

| Input word | Predicted context words |
| ---------- | ----------------------- |
| machine    | I, love, learning       |

The model learns to predict surrounding words from the target word.

Remember: Skip-Gram = Target → Context.

## Difference Between CBOW and Skip-Gram

| CBOW                                | Skip-Gram                   |
| ----------------------------------- | --------------------------- |
| Predicts target word                | Predicts context words      |
| Uses surrounding words as input     | Uses target word as input   |
| Context → Target                    | Target → Context            |
| Generally faster to train           | Generally slower to train   |
| Often works well for frequent words | Often useful for rare words |

# 6. What Is Positional Encoding?

Important for Transformers

## Definition

Positional encoding is a technique used in Transformer models to provide information about the position or order of tokens in a sequence.

In simple language:

Positional encoding tells the model where each word is located in a sentence.

## Why Do We Need Positional Encoding?

Consider these two sentences:

Sentence 1:

> Dog bites man.

Sentence 2:

> Man bites dog.

Both sentences contain exactly the same words.

But their meanings are completely different because the word order is different.

Now, consider a Transformer.

Unlike RNNs and LSTMs, Transformers process tokens in parallel rather than sequentially.

Therefore, the model needs additional information about the position of each token.

This is where positional encoding is used.

## Diagram: Without and With Positional Encoding

**Without positional encoding**

| Token | Representation |
| ----- | -------------- |
| Dog   | Embedding      |
| bites | Embedding      |
| man   | Embedding      |

The model receives word representations without explicit position information.

**With positional encoding**

| Token | Position | Position representation |
| ----- | -------- | ----------------------- |
| Dog   | 0        | Position vector         |
| bites | 1        | Position vector         |
| man   | 2        | Position vector         |

> **Final input representation = Word embedding + positional encoding**

Positional encoding provides the model with information about:

- Which word comes first.
- Which word comes second.
- Which word comes later.
- The relative or absolute positions of tokens.

## How Does Positional Encoding Work?

In the original Transformer architecture, positional encoding is calculated using sine and cosine functions.

There are two equations:

For even dimensions:

$$
PE(pos, 2i) = \sin\left(\frac{pos}{10000^{2i/d}}\right)
$$

For odd dimensions:

$$
PE(pos, 2i+1) = \cos\left(\frac{pos}{10000^{2i/d}}\right)
$$

Where:

| Symbol | Meaning                              |
| ------ | ------------------------------------ |
| pos    | Position of the word in the sentence |
| i      | Dimension index                      |
| d      | Embedding dimension                  |
| sin    | Sine function                        |
| cos    | Cosine function                      |

You do not need to memorize the mathematical equations initially. Understand the intuition first.

### Simple Numerical Example

Suppose we have three words:

`I love NLP`

Assume each word has a 4-dimensional embedding.

| Word | Position |
| ---- | -------- |
| I    | 0        |
| love | 1        |
| NLP  | 2        |

The positional encoding generates a separate vector for each position.

For illustration:

| Position | Positional encoding       |
| -------- | ------------------------- |
| 0        | [0, 1, 0, 1]              |
| 1        | [0.84, 0.54, 0.01, 1.00]  |
| 2        | [0.91, -0.42, 0.02, 1.00] |

These are rounded illustrative values.

Now, the Transformer adds the positional encoding to the word embedding.

$$
\text{Final Input} = \text{Word Embedding} + \text{Positional Encoding}
$$

For example:

$$
E_{\text{love}} = [0.2, 0.5, 0.3, 0.1]
$$

$$
PE_1 = [0.84, 0.54, 0.01, 1.00]
$$

Then:

$$
X_{\text{love}} = [1.04, 1.04, 0.31, 1.10]
$$

This final vector contains both word information and position information.

## Types of Positional Encoding

1. Sinusoidal positional encoding: Uses sine and cosine functions. Used in the original Transformer.
2. Learned positional encoding: Position vectors are learned during model training.
3. Relative positional encoding: Represents the relative distance or position between tokens.
4. Rotary positional encoding (RoPE): Incorporates position information by rotating query and key vectors in attention mechanisms.

# 7. How Embeddings and Positional Encoding Work Together in Transformers

This is the most important connection.

Consider the sentence:

> "I love Natural Language Processing."

A Transformer processes it through the following steps.

Input Sentence

## I love NLP

Tokenization

["I", "love", "NLP"]

Embedding Layer

Converts each token into a dense vector.

Positional Encoding

Generates position vectors.

Addition

$X = E + PE$

Word embeddings + positional encoding

Transformer Encoder

Self-attention + Feed Forward Network

Contextual Representations

Each token gets a representation influenced by other tokens.

### What happens after positional encoding?

The Transformer uses self-attention to understand relationships between words.

For example:

> "The animal didn't cross the street because it was tired."

Self-attention helps the model understand which words are related and that "it" refers to "animal".

The representation of a token can now depend on the other words in the sentence.

This is called a contextual embedding.

# 8. Difference Between Embeddings, Word2Vec and Positional Encoding

This is a common interview question.

| Feature               | Embeddings                       | Word2Vec                           | Positional Encoding             |
| --------------------- | -------------------------------- | ---------------------------------- | ------------------------------- |
| Meaning               | Numerical representation of data | Technique to learn word embeddings | Represents token positions      |
| Purpose               | Convert words into dense vectors | Learn semantic relationships       | Preserve word order information |
| Input                 | Words/tokens                     | Training text and context          | Position indices                |
| Output                | Dense vectors                    | Learned word vectors               | Position vectors                |
| Used in               | NLP, CV, Transformers            | Traditional NLP                    | Transformers                    |
| Captures word meaning | Can capture it                   | Yes, based on usage patterns       | No                              |
| Captures word order   | Not inherently                   | Not inherently                     | Yes                             |

Important relationship:

- Embeddings represent what a word means.
- Word2Vec is one method of learning word embeddings.
- Positional encoding represents where a word occurs in a sentence.

# 9. Interview Questions and Answers

Q1. What are embeddings in NLP?

Embeddings are dense numerical vectors representing words or tokens in a continuous vector space, where semantic relationships can be captured.

Q2. What is Word2Vec?

Word2Vec is a neural network-based technique that learns word embeddings by predicting a target word from its context or predicting context words from a target word.

Q3. What are the two architectures of Word2Vec?

- CBOW: Predicts a target word from surrounding context words.
- Skip-Gram: Predicts surrounding context words from a target word.

Q4. Why do we need positional encoding in Transformers?

Transformers process tokens in parallel and do not inherently encode their sequence order. Positional encoding provides information about token positions.

Q5. What is the difference between Word2Vec and BERT embeddings?

Word2Vec generates a relatively fixed embedding for a word regardless of its sentence. BERT generates contextual embeddings, meaning the representation changes depending on the surrounding words.

Q6. Does Word2Vec understand the context of a sentence?

Word2Vec learns from local surrounding-word patterns during training, but it does not generate different contextual representations for the same word in different sentences.

# 10. Quick Revision

Embeddings

Convert words into meaningful numerical vectors.

Word2Vec

Learns word embeddings using surrounding words.

Positional Encoding

Provides information about the position of each token.

## Test Your Understanding