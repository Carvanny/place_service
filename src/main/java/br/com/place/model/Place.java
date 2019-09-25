/**
 * 
 */
package br.com.place.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Glaucio
 *
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "place")
@ApiModel(value = "Places", description = "This is a simple challenge to test your skills on building APIs.")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Place implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@ApiModelProperty(value = "name", example = "bangu")
	private String name;
	@ApiModelProperty(value = "slug", example = "lesma")
	private String slug;
	@ApiModelProperty(value = "city", example = "Rio de Janeiro")
	private String city;
	@ApiModelProperty(value = "state", example = "RJ")
	private String state;
	private Date created;
	private Date updated;

}
