package com.ey.springboot3security.service;

import com.ey.springboot3security.entity.Product;
import com.ey.springboot3security.entity.ProductDTO;
import com.ey.springboot3security.entity.UserInfo;
import com.ey.springboot3security.repository.ProductRepository;
import com.ey.springboot3security.repository.UserInfoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.*;
import org.springframework.util.ReflectionUtils;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import jakarta.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Set;


@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;


    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product save(Product product) {
        return productRepository.save(product);


    }

    // public Product save(Product product) {
    //     // Obtain the currently logged-in user's information
    //     UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    //     String currentUsername = userDetails.getUsername();
    //       // Find the user based on the username
    //     UserInfo user = userInfoRepository.findByEmail(currentUsername)
    //             .orElseThrow(() -> new EntityNotFoundException("User with email " + currentUsername + " not found"));
    //  System.out.println("user: " + user);
    //      // Set the user as the owner of the product
    //     product.setUser(user);

    //     return productRepository.save(product);


    // }


    public void delete(Integer id) {
        productRepository.deleteById(id);
    }

    public Product get(Integer id) {
        return productRepository.findById(id).get();
    }


 public Product patchProduct(Integer id, ProductDTO productUpdateDTO) {
        Product existingProduct = productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Product with id " + id + " not found")
        );

        // Use a utility method to copy non-null properties from productUpdateDTO to existingProduct
        copyNonNullProperties(productUpdateDTO, existingProduct);

        return productRepository.save(existingProduct);
    }

   // Utility method to copy non-null properties from source to target object
private void copyNonNullProperties(Object source, Object target) {
    BeanUtils.copyProperties(source, target, getNullAndNonClassPropertyNames(source));
}

// Utility method to get null property names and exclude 'class'
private String[] getNullAndNonClassPropertyNames(Object source) {
    final BeanWrapper src = new BeanWrapperImpl(source);
    java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

    Set<String> emptyAndNonClassNames = new HashSet<>();
    for (java.beans.PropertyDescriptor pd : pds) {
        String propertyName = pd.getName();
        Object srcValue = src.getPropertyValue(propertyName);

        if (srcValue != null && !"class".equals(propertyName)) {
            
                emptyAndNonClassNames.add(propertyName);
            
        }
    }

    String[] result = new String[emptyAndNonClassNames.size()];
    return emptyAndNonClassNames.toArray(result);
}



   

  

}