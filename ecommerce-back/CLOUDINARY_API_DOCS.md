# API de Cloudinary para E-commerce

## Descripción
Este proyecto implementa la funcionalidad de subida de imágenes usando Cloudinary para productos y categorías. La implementación permite:

- Subir múltiples imágenes por producto
- Subir múltiples imágenes por categoría
- Marcar imágenes como principales
- Eliminar imágenes tanto de Cloudinary como de la base de datos
- Compatibilidad con el sistema existente de imágenes principales de categorías

## Endpoints Disponibles

### Productos

#### Subir imagen de producto
```
POST /api/admin/products/{productId}/images
Content-Type: multipart/form-data

Parámetros:
- file: MultipartFile (requerido)
- isMain: Boolean (opcional, default: false)
- name: String (opcional)

Respuesta:
{
  "id": 1,
  "imageUrl": "https://res.cloudinary.com/...",
  "publicId": "Ecommerce/products/...",
  "name": "imagen.jpg",
  "isMain": false,
  "productId": 1,
  "state": true
}
```

#### Obtener imágenes de un producto
```
GET /api/admin/products/{productId}/images

Respuesta:
[
  {
    "id": 1,
    "imageUrl": "https://res.cloudinary.com/...",
    "publicId": "Ecommerce/products/...",
    "name": "imagen.jpg",
    "isMain": true,
    "productId": 1,
    "state": true
  }
]
```

#### Marcar imagen como principal
```
PUT /api/admin/products/images/{galleryId}/main?productId={productId}

Respuesta: 200 OK
```

#### Eliminar imagen de producto
```
DELETE /api/admin/products/images/{galleryId}?publicId={publicId}

Respuesta: 204 No Content
```

### Categorías

#### Subir imagen de categoría
```
POST /api/admin/categories/{categoryId}/images
Content-Type: multipart/form-data

Parámetros:
- file: MultipartFile (requerido)
- isMain: Boolean (opcional, default: false)
- name: String (opcional)

Respuesta:
{
  "id": 1,
  "imageUrl": "https://res.cloudinary.com/...",
  "publicId": "Ecommerce/categories/...",
  "name": "categoria.jpg",
  "isMain": false,
  "categoryId": 1,
  "state": true
}
```

#### Obtener imágenes de una categoría
```
GET /api/admin/categories/{categoryId}/images

Respuesta:
[
  {
    "id": 1,
    "imageUrl": "https://res.cloudinary.com/...",
    "publicId": "Ecommerce/categories/...",
    "name": "categoria.jpg",
    "isMain": true,
    "categoryId": 1,
    "state": true
  }
]
```

#### Marcar imagen como principal
```
PUT /api/admin/categories/images/{galleryId}/main?categoryId={categoryId}

Respuesta: 200 OK
```

#### Eliminar imagen de categoría
```
DELETE /api/admin/categories/images/{galleryId}?publicId={publicId}

Respuesta: 204 No Content
```

#### Subir imagen principal de categoría (compatibilidad)
```
POST /api/admin/categories/{categoryId}/main-image
Content-Type: multipart/form-data

Parámetros:
- file: MultipartFile (requerido)

Respuesta: 200 OK
```

### Endpoints Públicos

#### Obtener imágenes de producto (público)
```
GET /api/public/productgalleries/product/{productId}

Respuesta:
[
  {
    "id": 1,
    "imageUrl": "https://res.cloudinary.com/...",
    "publicId": "Ecommerce/products/...",
    "name": "imagen.jpg",
    "isMain": true,
    "productId": 1
  }
]
```

#### Obtener imágenes de categoría (público)
```
GET /api/public/categorygalleries/category/{categoryId}

Respuesta:
[
  {
    "id": 1,
    "imageUrl": "https://res.cloudinary.com/...",
    "publicId": "Ecommerce/categories/...",
    "name": "categoria.jpg",
    "isMain": true,
    "categoryId": 1
  }
]
```

#### Obtener imagen principal de categoría (público)
```
GET /api/public/categorygalleries/category/{categoryId}/main

Respuesta:
{
  "id": 1,
  "imageUrl": "https://res.cloudinary.com/...",
  "publicId": "Ecommerce/categories/...",
  "name": "categoria.jpg",
  "isMain": true,
  "categoryId": 1
}
```

## Configuración

### application.properties
```properties
# Cloudinary Configuration
cloudinary.cloud-name=tu_cloud_name
cloudinary.api-key=tu_api_key
cloudinary.api-secret=tu_api_secret

# File Upload Limits
spring.servlet.multipart.max-file-size=5MB
spring.servlet.multipart.max-request-size=10MB
```

### Extensiones permitidas
- jpg
- jpeg
- png
- webp
- avif

## Estructura de Base de Datos

### ProductGallery
- id: Long (PK)
- imageUrl: String
- publicId: String
- name: String
- isMain: Boolean
- product: Product (FK)
- state: Boolean (soft delete)

### CategoryGallery
- id: Long (PK)
- imageUrl: String
- publicId: String
- name: String
- isMain: Boolean
- category: Category (FK)
- state: Boolean (soft delete)

## Características Implementadas

1. **Gestión de imágenes principales**: Solo una imagen puede ser marcada como principal por producto/categoría
2. **Soft delete**: Las imágenes se marcan como eliminadas en lugar de borrarse físicamente
3. **Validación de extensiones**: Solo se permiten formatos de imagen válidos
4. **Límites de tamaño**: Configurables en application.properties
5. **Organización por carpetas**: Las imágenes se organizan en carpetas por tipo (products/categories)
6. **Compatibilidad**: Mantiene compatibilidad con el sistema existente de imágenes principales de categorías

## Ejemplos de Uso

### Frontend (JavaScript)
```javascript
// Subir imagen de producto
const formData = new FormData();
formData.append('file', fileInput.files[0]);
formData.append('isMain', 'true');
formData.append('name', 'imagen-principal');

fetch(`/api/admin/products/${productId}/images`, {
  method: 'POST',
  body: formData
})
.then(response => response.json())
.then(data => console.log('Imagen subida:', data));

// Marcar imagen como principal
fetch(`/api/admin/products/images/${galleryId}/main?productId=${productId}`, {
  method: 'PUT'
})
.then(response => console.log('Imagen marcada como principal'));

// Eliminar imagen
fetch(`/api/admin/products/images/${galleryId}?publicId=${publicId}`, {
  method: 'DELETE'
})
.then(response => console.log('Imagen eliminada'));
```
