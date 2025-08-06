function ProductList({products}) {
    if (products.length === 0) {return <p>No product found.</p>}
    return (
        <div className="container">
      <div className="row">
        {products.map((product) => (
          <div className="col-md-4 mb-4" key={product.id}>
            <ProductCard product={product} />
          </div>
        ))}
      </div>
    </div>
    );

}
export default ProductList