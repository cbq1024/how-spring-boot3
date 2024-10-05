import Image from 'next/image';
import styles from './index.module.css';

interface CustomImageProps {
  src: string;
  alt: string;
  width?: number;
  height?: number;
}

const CustomImage = ({
  src,
  alt,
  width = 500,
  height = 300,
}: CustomImageProps) => {
  return (
    <Image
      src={src}
      alt={alt}
      width={width}
      height={height}
      className={styles.image} // 应用样式
    />
  );
};

export default CustomImage;
