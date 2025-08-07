export const handleTelegramContact = (telegramHandle) => {
  const cleanHandle = telegramHandle.replace('@', '');
  window.open(`https://t.me/${cleanHandle}`, '_blank');
};