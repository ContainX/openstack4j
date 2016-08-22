package org.openstack4j.api.image.v2;

import org.openstack4j.common.RestService;

/**
 * Image (Glance) V2 Metadata Api
 * @author emjburns
 */
public interface MetadataService extends RestService {

//    GET/v2/metadefs/resource_typesList resource types
//    Lists all resource types.
//
//    POST/v2/metadefs/namespaces/​{namespace_id}​/resource_typesCreate resource type association
//    Creates a resource type association in a namespace.
//
//    GET/v2/metadefs/namespaces/​{namespace_id}​/resource_typesList resource type associations
//    Lists resource type associations in a namespace.
//
//    DELETE/v2/metadefs/namespaces/​{namespace_id}​/resource_types/​{name}​Remove resource type association
//    Removes a resource type association in a namespace.
//
//    POST/v2/metadefs/namespacesCreate namespace
//    Creates a namespace.
//
//    GET/v2/metadefs/namespacesList namespaces
//    Lists public namespaces.
//
//    GET/v2/metadefs/namespaces/​{namespace_id}​Get namespaces details
//    Gets details for a namespace.
//
//    PUT/v2/metadefs/namespaces/​{namespace_id}​Update namespace
//    Updates a namespace.
//
//    DELETE/v2/metadefs/namespaces/​{namespace_id}​Delete namespace
//    Deletes a namespace and its properties, objects, and any resource type associations.
//
//    POST/v2/metadefs/namespaces/​{namespace_id}​/propertiesCreate property
//    Creates a property definition in a namespace.
//
//    GET/v2/metadefs/namespaces/​{namespace_id}​/propertiesList property definitions
//    Lists property definitions in a namespace.
//
//    GET/v2/metadefs/namespaces/​{namespace_id}​/properties/​{property_name}​Show property definition
//    Shows the definition for a property.
//
//    PUT/v2/metadefs/namespaces/​{namespace_id}​/properties/​{property_name}​Update property definition
//    Updates a property definition.
//
//    DELETE/v2/metadefs/namespaces/​{namespace_id}​/properties/​{property_name}​Remove property definition
//    Removes a property definition in a namespace.
//
//    POST/v2/metadefs/namespaces/​{namespace_id}​/objectsCreate object
//    Creates an object definition in a namespace.
//
//    GET/v2/metadefs/namespaces/​{namespace_id}​/objectsList object definitions
//    Lists object definitions in a namespace.
//
//    GET/v2/metadefs/namespaces/​{namespace_id}​/objects/​{object_name}​Show object definition
//    Shows the definition for an object.
//
//    PUT/v2/metadefs/namespaces/​{namespace_id}​/objects/​{object_name}​Update object definition
//    Updates an object definition in a namespace.
//
//    DELETE/v2/metadefs/namespaces/​{namespace_id}​/objects/​{object_name}​Delete property definition
//    Deletes an object definition from a namespace.
//
//    POST/v2/metadefs/namespaces/tags/​{namespace_id}​Create tags
//    Creates one or more tag definitions in a namespace.
//
//    GET/v2/metadefs/namespaces/tags/​{namespace_id}​List tags
//    Lists the tag definitions within a namespace.
//
//    DELETE/v2/metadefs/namespaces/tags/​{namespace_id}​Delete all tag definitions
//    Deletes all tag definitions within a namespace.
//
//    POST/v2/metadefs/namespaces/tags/​{namespace_id}​/​{tag_name}​Add tag definition
//    Adds a tag to the list of namespace tag definitions.
//
//    GET/v2/metadefs/namespaces/tags/​{namespace_id}​/​{tag_name}​Get tag definition
//    Gets a definition for a tag.
//
//    PUT/v2/metadefs/namespaces/tags/​{namespace_id}​/​{tag_name}​Update tag definition
//    Renames a tag definition.
//
//    DELETE/v2/metadefs/namespaces/tags/​{namespace_id}​/​{tag_name}​Delete tag definition
//    Deletes a tag definition within a namespace.
}
