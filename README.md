# Word Tokenizer Server

This web application tokenizers Vietnamese text using [VnCoreNLP](https://github.com/vncorenlp/VnCoreNLP) library.

## Setup

1. Copy [VnCoreNLP models](https://github.com/vncorenlp/VnCoreNLP/tree/master/models) to `<tomcat location>/bin`.
2. Copy `VnCoreNLP-1.1.1.jar` to `<tomcat location>/lib`.
3. Run the web application.

## Usage

```javascript
const input = "Ông Nguyễn Khắc Chúc  đang làm việc tại Đại học Quốc gia Hà Nội. Bà Lan, vợ ông Chúc, cũng làm việc tại đây.";
const requestOptions = {
  method: 'POST',
  headers: new Headers({"Content-Type": "text/plain;charset=utf-8"}),
  body: input
};

fetch("http://localhost:8080/<context>/word_tokenizer", requestOptions)
  .then(response => response.text())
  .then(result => console.log(result));
```