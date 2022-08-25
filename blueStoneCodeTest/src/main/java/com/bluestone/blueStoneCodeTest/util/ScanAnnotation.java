package com.bluestone.blueStoneCodeTest.util;

import java.lang.annotation.Annotation;
import java.util.Set;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ScanAnnotation {

	/*
	 * to find the classes that are custom annotated for the given package
	 */
	public void findAnnotatedClasses(Class<? extends Annotation> annotationType, String packagesToBeScanned) {
		ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
		provider.addIncludeFilter(new AnnotationTypeFilter(annotationType));
		Set<BeanDefinition> beanDefs = provider.findCandidateComponents(packagesToBeScanned);
		beanDefs.stream().forEach(b -> logAnnotatedClasses(b));
	}

	/*
	 * to write output for class name and annotated parameter 'name'
	 */
	private void logAnnotatedClasses(BeanDefinition beanDef) {
		try {
			Class<?> cl = Class.forName(beanDef.getBeanClassName());
			Custom custom = cl.getAnnotation(Custom.class);
			log.info("[name = {}, className = {}]", custom.name(), cl.getSimpleName());
		} catch (Exception e) {
			log.error("There is an exception in finding annotated classes");
		}
	}
}
