(async() => {
  const res = await fetch('http://localhost:9000/api/v2-4/books/1', { method: 'DELETE' });

  const result = await res.text();
  console.log(result);
})();